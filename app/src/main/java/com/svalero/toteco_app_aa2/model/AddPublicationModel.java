package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.AddPublicationContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.domain.localdb.ProductLocal;
import com.svalero.toteco_app_aa2.domain.localdb.PublicationLocal;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;

public class AddPublicationModel implements AddPublicationContract.Model {

    private final AppDatabase db;
    private double totalPrice = 0;
    private double totalPunctuation = 0;

    public AddPublicationModel(Context context) {
        db = Room.databaseBuilder(context,
                        AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
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
        if (productLocals.size() != 0) {
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
    public void onPressSubmit(AddPublicationDTO addPublicationDTO) {
        EstablishmentLocal establishment = addPublicationDTO.getEstablishment();
//        String p = addPublicationDTO.getEstablishmentPunctuation();
//        double newEstablishmentPunctuation = Utils.roundNumber(Float.parseFloat(p));
//        AddPublicationSummaryDTO summary = makeSummary(newEstablishmentPunctuation);
        List<ProductLocal> productLocals = loadProducts();
//        double establishmentPunctuation = summary.getTotalPunctuation();

        // if the establishment doesnt exists we create it
        if (establishment.getId() == 1) {
            EstablishmentLocal newEstablishment = new EstablishmentLocal(
//                    establishment.getName(),
//                    establishment.getLatitude(),
//                    establishment.getLongitude(),
//                    true,
//                    (float) totalPunctuation
            );
            db.establishmentDao().insert(newEstablishment);
            establishment = db.establishmentDao().findLast();
        } else {
            // Recalculate the establishment punctuation
            int establishmentPublications = db.establishmentDao().countPublicationsByEstablishmentId(establishment.getId()) + 1;
            EstablishmentLocal e = db.establishmentDao().findById(establishment.getId());
            float punctuation = (float) ((e.getPunctuation() + totalPunctuation) / establishmentPublications);
            System.out.println(punctuation);
            establishment.setPunctuation(punctuation);
            db.establishmentDao().update(establishment);
        }

        PublicationLocal publicationLocal = new PublicationLocal(
//                (float) totalPrice,
//                (float) totalPunctuation,
//                1,
//                establishment.getId()
        );

        publicationLocal.setPhoto(addPublicationDTO.getImage());

        db.publicationDao().insert(publicationLocal);
        PublicationLocal addedPublicationLocal = db.publicationDao().findLast();

        productLocals.stream().forEach(p -> {
            p.setPublicationId(addedPublicationLocal.getId());
            db.productDao().update(p);
        });

    }

    @Override
    public EstablishmentLocal clearEstablishmentAux() {
        EstablishmentLocal establishment = new EstablishmentLocal(
//                "",
//                0,
//                0,
//                true,
//                0
        );
        establishment.setId(1);
        db.establishmentDao().update(establishment);

        return establishment;
    }

    @Override
    public EstablishmentLocal getEstablishment() {
        try {
            EstablishmentLocal establishment = db.establishmentDao().findById(1);
            if (!establishment.getName().equals("")) {
                List<EstablishmentLocal> exists = db.establishmentDao().findByNameExceptAux(establishment.getName());
                if (exists.size() != 0) {
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
}