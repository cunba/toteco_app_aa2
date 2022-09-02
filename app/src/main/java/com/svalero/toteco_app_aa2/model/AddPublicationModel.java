package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.svalero.toteco_app_aa2.contract.AddPublicationContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.Product;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationDTO;
import com.svalero.toteco_app_aa2.domain.dto.AddPublicationSummaryDTO;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;

import okhttp3.internal.Util;

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
    public List<Product> loadProducts() {
        try {
            return db.productDao().findByPublicationId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AddPublicationSummaryDTO makeSummary(double establishmentPunctuation) {
        List<Product> products = loadProducts();
        if (products.size() != 0) {
            totalPrice = products.stream()
                    .map(Product::getPrice)
                    .mapToDouble(price -> price)
                    .sum();
            totalPrice = Utils.roundNumber(totalPrice);

            totalPunctuation = (products.stream()
                    .map(Product::getPunctuation)
                    .mapToDouble(punctuation -> punctuation)
                    .sum() + establishmentPunctuation) / (products.size() + 1);
            totalPunctuation = Utils.roundNumber(totalPunctuation);
            return new AddPublicationSummaryDTO(totalPrice, totalPunctuation);
        }

        return new AddPublicationSummaryDTO(0, 0);
    }

    @Override
    public void onPressSubmit(AddPublicationDTO addPublicationDTO) {
        Establishment establishment = addPublicationDTO.getEstablishment();
//        String p = addPublicationDTO.getEstablishmentPunctuation();
//        double newEstablishmentPunctuation = Utils.roundNumber(Float.parseFloat(p));
//        AddPublicationSummaryDTO summary = makeSummary(newEstablishmentPunctuation);
        List<Product> products = loadProducts();
//        double establishmentPunctuation = summary.getTotalPunctuation();

        // if the establishment doesnt exists we create it
        if (establishment.getId() == 1) {
            Establishment newEstablishment = new Establishment(
                    establishment.getName(),
                    establishment.getLatitude(),
                    establishment.getLongitude(),
                    true,
                    (float) totalPunctuation
            );
            db.establishmentDao().insert(newEstablishment);
            establishment = db.establishmentDao().findLast();
        } else {
            // Recalculate the establishment punctuation
            int establishmentPublications = db.establishmentDao().countPublicationsByEstablishmentId(establishment.getId()) + 1;
            Establishment e = db.establishmentDao().findById(establishment.getId());
            float punctuation = (float) ((e.getPunctuation() + totalPunctuation) / establishmentPublications);
            System.out.println(punctuation);
            establishment.setPunctuation(punctuation);
            db.establishmentDao().update(establishment);
        }

        Publication publication = new Publication(
                (float) totalPrice,
                (float) totalPunctuation,
                1,
                establishment.getId());

        publication.setImage(addPublicationDTO.getImage());

        db.publicationDao().insert(publication);
        Publication addedPublication = db.publicationDao().findLast();

        products.stream().forEach(p -> {
            p.setPublicationId(addedPublication.getId());
            db.productDao().update(p);
        });

    }

    @Override
    public Establishment clearEstablishmentAux() {
        Establishment establishment = new Establishment(
                "",
                0,
                0,
                true,
                0
        );
        establishment.setId(1);
        db.establishmentDao().update(establishment);

        return establishment;
    }

    @Override
    public Establishment getEstablishment() {
        try {
            Establishment establishment = db.establishmentDao().findById(1);
            if (!establishment.getName().equals("")) {
                List<Establishment> exists = db.establishmentDao().findByNameExceptAux(establishment.getName());
                if (exists.size() != 0) {
                    exists.stream().forEach(e -> {
                        if (e.getLatitude() == establishment.getLatitude() && e.getLongitude() == establishment.getLongitude()) {
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