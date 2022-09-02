package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.HomeContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.dto.PublicationToRecyclerView;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeModel implements HomeContract.Model {

    private final AppDatabase db;
    private final Context context;

    public HomeModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        this.context = context;
    }

    @Override
    public List<Publication> loadPublications() {
        return db.publicationDao().findAllExceptAux();
    }

    @Override
    public void deleteUnsavedProducts() {
        try {
            db.productDao().deleteByPublicationId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PublicationToRecyclerView> convertPublications() {
        List<Publication> publications = loadPublications();
        List<PublicationToRecyclerView> publicationsToRecyclerView = new ArrayList<>();
        publications.stream().forEach(p -> {
            Establishment establishment = db.establishmentDao().findById(p.getEstablishmentId());
            List<Product> products = db.productDao().findByPublicationId(p.getId());
            double totalPrice = Utils.roundNumber(p.getTotalPrice());
            double totalPunctuation = Utils.roundNumber(p.getTotalPunctuation());
            PublicationToRecyclerView publicationToRecyclerView = new PublicationToRecyclerView(
                    p.getId(),
                    establishment.getName(),
                    String.valueOf(establishment.getPunctuation()),
                    p.getImage(),
                    products,
                    context.getString(R.string.card_price, String.valueOf(totalPrice)),
                    context.getString(R.string.card_punctuation, String.valueOf(totalPunctuation))
            );
            publicationsToRecyclerView.add(publicationToRecyclerView);
        });
        return publicationsToRecyclerView;
    }
}