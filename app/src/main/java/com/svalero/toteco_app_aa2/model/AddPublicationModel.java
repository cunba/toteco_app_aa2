package com.svalero.toteco_app_aa2.model;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.AddPublicationContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.dto.EstablishmentDTO;
import com.svalero.toteco_app_aa2.domain.dto.ProductDTO;
import com.svalero.toteco_app_aa2.domain.dto.PublicationDTO;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.domain.dto.view.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.domain.utils.Location;
import com.svalero.toteco_app_aa2.presenter.AddPublicationPresenter;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPublicationModel implements AddPublicationContract.Model,
        AddPublicationContract.Model.CreateEstablishmentListener, AddPublicationContract.Model.CreatePublicationListener,
        AddPublicationContract.Model.CreateProductsListener {

    private final AppDatabase db;
    private double totalPrice = 0;
    private double totalPunctuation = 0;
    private final TotecoApiInterface api;
    private final Context context;
    private final AddPublicationPresenter presenter;
    private boolean submitError;

    public AddPublicationModel(Context context, AddPublicationPresenter presenter) {
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        api = TotecoApi.buildInstance();
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public List<ProductLocal> loadProducts() {
        try {
            return db.productDao().findByPublicationId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AddPublicationSummaryDTO makeSummary(double establishmentPunctuation) {
        List<ProductLocal> productLocals = loadProducts();
        if (productLocals.isEmpty()) {
            totalPrice = productLocals.stream()
                    .map(ProductLocal::getPrice)
                    .mapToDouble(price -> price)
                    .sum();
            totalPrice = Utils.roundNumber(totalPrice);

            totalPunctuation = (productLocals.stream()
                    .map(ProductLocal::getPunctuation)
                    .mapToDouble(punctuation -> punctuation)
                    .sum() + establishmentPunctuation) / (productLocals.size() + 1);
            totalPunctuation = Utils.roundNumber(totalPunctuation);
            return new AddPublicationSummaryDTO(totalPrice, totalPunctuation);
        }

        return new AddPublicationSummaryDTO(0, 0);
    }

    @Override
    public void onPressSubmit(String image) {
        EstablishmentLocal establishment = db.establishmentDao().findAll().get(0);
        makeSummary(establishment.getPunctuation());

        // if the establishment does not exists we create it
        if (establishment.getId() == 0) {
            EstablishmentDTO establishmentDTO = new EstablishmentDTO(
                    establishment.getName(),
                    new Location((float) establishment.getLatitude(), (float) establishment.getLongitude()),
                    true
            );
            createEstablishment(this, establishmentDTO, image);
            return;
        }

        UserLocal user = Utils.getUserLogged(context);
        PublicationDTO publicationDTO = new PublicationDTO(
                image,
                user.getId(),
                establishment.getId()
        );
        createPublication(this, publicationDTO);
    }

    @Override
    public void createPublication(CreatePublicationListener listener, PublicationDTO publicationDTO) {
        UserLocal user = Utils.getUserLogged(context);
        String authorization = "Bearer " + user.getToken();
        Call<Publication> call = api.createPublication(authorization, publicationDTO);
        call.enqueue(new Callback<Publication>() {
            @Override
            public void onResponse(Call<Publication> call, Response<Publication> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.createPublicationError(error);
                    return;
                }

                listener.createPublicationSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Publication> call, Throwable t) {
                listener.createPublicationError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public void createPublicationSuccess(Publication publication) {
        createProducts(this, publication.getId());
    }

    @Override
    public void createPublicationError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createEstablishment(CreateEstablishmentListener listener, EstablishmentDTO establishmentDTO, String image) {
        UserLocal user = Utils.getUserLogged(context);
        String authorization = "Bearer " + user.getToken();
        Call<Establishment> call = api.createEstablishment(authorization, establishmentDTO);
        call.enqueue(new Callback<Establishment>() {
            @Override
            public void onResponse(Call<Establishment> call, Response<Establishment> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.createEstablishmentError(error);
                    return;
                }

                listener.createEstablishmentSuccess(response.body(), image);
            }

            @Override
            public void onFailure(Call<Establishment> call, Throwable t) {
                listener.createEstablishmentError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public void createEstablishmentSuccess(Establishment establishment, String image) {
        clearEstablishmentAux();
        UserLocal user = Utils.getUserLogged(context);
        PublicationDTO publicationDTO = new PublicationDTO(
                image,
                user.getId(),
                establishment.getId()
        );
        createPublication(this, publicationDTO);
    }

    @Override
    public void createEstablishmentError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    private void clearEstablishmentAux() {
        List<EstablishmentLocal> establishments = db.establishmentDao().findAll();
        establishments.stream().forEach(e -> db.establishmentDao().delete(e));
    }

    private void clearProductsAux() {
        List<ProductLocal> products = db.productDao().findAll();
        products.stream().forEach(p -> db.productDao().delete(p));
    }

    @Override
    public EstablishmentLocal getEstablishment() {
        try {
            EstablishmentLocal establishment = db.establishmentDao().findAll().get(0);
            if (!establishment.getName().equals("")) {
                List<EstablishmentLocal> exists = db.establishmentDao().findByNameExceptAux(establishment.getName());
                if (!exists.isEmpty()) {
                    exists.stream().forEach(e -> {
                        if (e.getLatitude() == establishment.getLatitude() &&
                                e.getLongitude() == establishment.getLongitude()) {
                            establishment.setId(e.getId());
                            establishment.setOpen(e.isOpen());
                        }
                    });
                }
            }
            return establishment;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createProducts(CreateProductsListener listener, int publicationId) {
        UserLocal user = Utils.getUserLogged(context);
        String authorization = "Bearer " + user.getToken();
        List<ProductLocal> products = db.productDao().findAll();
        products.stream().forEach(p -> {
            submitError = true;
            ProductDTO productDTO = new ProductDTO(p);
            productDTO.setPublicationId(publicationId);
            Call<Product> call = api.createProduct(authorization, productDTO);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (!response.isSuccessful()) {
                        String error = Utils.getErrorResponse(response.errorBody().charStream());
                        listener.createProductsError(error);
                        return;
                    }

                    listener.createProductsSuccess();
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    listener.createProductsError(context.getString(R.string.error_database));
                }
            });
        });

        if (!submitError) {
            clearProductsAux();
            presenter.onSubmit(context.getString(R.string.add_publication_success));
        }
    }

    @Override
    public void createProductsSuccess() {
        submitError = false;
    }

    @Override
    public void createProductsError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
}