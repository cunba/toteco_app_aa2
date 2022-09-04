package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.dialog.ProductDialogContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.ProductType;
import com.svalero.toteco_app_aa2.domain.dto.view.ProductDialogDTO;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDialogModel implements ProductDialogContract.Model {

    private final AppDatabase db;
    private final Context context;
    private final TotecoApiInterface api;

    public ProductDialogModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        this.context = context;
        api = TotecoApi.buildInstance();
    }

    @Override
    public void getTypes(GetTypesListener listener) {
        Call<List<ProductType>> call = api.getAllProductTypes();
        call.enqueue(new Callback<List<ProductType>>() {
            @Override
            public void onResponse(Call<List<ProductType>> call, Response<List<ProductType>> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.getTypesError(error);
                    return;
                }

                listener.getTypesSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductType>> call, Throwable t) {
                t.printStackTrace();
                listener.getTypesError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public String addProduct(ProductDialogDTO productDialogDTO) {
        if (productDialogDTO.getType() == null) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPrice().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPunctuation().equals("")) {
            return context.getString(R.string.error_field_empty);
        }

        float price = Float.parseFloat(productDialogDTO.getPrice());
        float punctuation = Float.parseFloat(productDialogDTO.getPunctuation());

        if (price < 0.0 || punctuation < 0.0) {
            return context.getString(R.string.add_product_error_price_punctuation);
        }
        if (punctuation > 5.0) {
            return context.getString(R.string.add_product_error_punctuation);
        }

        ProductLocal newProductLocal = new ProductLocal();
        newProductLocal.setName(productDialogDTO.getType().getProductName() + " " + productDialogDTO.getType().getType());
        newProductLocal.setPrice(price);
        newProductLocal.setPunctuation(punctuation);
        newProductLocal.setTypeId(productDialogDTO.getType().getId());
        db.productDao().insert(newProductLocal);
        return "";
    }

    @Override
    public String modifyProduct(ProductDialogDTO productDialogDTO, ProductLocal productLocal) {
        if (productDialogDTO.getType() == null) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPrice().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (productDialogDTO.getPunctuation().equals("")) {
            return context.getString(R.string.error_field_empty);
        }

        float price = Float.parseFloat(productDialogDTO.getPrice());
        float punctuation = Float.parseFloat(productDialogDTO.getPunctuation());

        if (price < 0.0 || punctuation < 0.0) {
            return context.getString(R.string.add_product_error_price_punctuation);
        }
        if (punctuation > 5.0) {
            return context.getString(R.string.add_product_error_punctuation);
        }

        productLocal.setName(productDialogDTO.getType().getProductName() + " " + productDialogDTO.getType().getType());
        productLocal.setPrice(price);
        productLocal.setPunctuation(punctuation);
        productLocal.setTypeId(productDialogDTO.getType().getId());
        db.productDao().update(productLocal);
        return "";
    }
}
