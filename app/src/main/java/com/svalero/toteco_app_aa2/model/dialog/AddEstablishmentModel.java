package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.dialog.AddEstablishmentContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.AddEstablishmentDTO;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;

import java.util.List;

public class AddEstablishmentModel implements AddEstablishmentContract.Model {
    private final AppDatabase db;
    private final Context context;

    public AddEstablishmentModel(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public List<EstablishmentLocal> loadEstablishments() {
        return db.establishmentDao().findAllExceptAux();
    }

    @Override
    public String onPressSubmit(AddEstablishmentDTO addEstablishmentDTO) {
        if (addEstablishmentDTO.getName().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (addEstablishmentDTO.getPunctuation().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        EstablishmentLocal establishment = addEstablishmentDTO.getEstablishment();
        float punctuation = Float.parseFloat(addEstablishmentDTO.getPunctuation());
        if (punctuation > 5) {
            return context.getString(R.string.add_product_error_punctuation);
        }

        establishment.setPunctuation(punctuation);

        if (establishment.getId() == 0) {
            establishment.setName(addEstablishmentDTO.getName());
        }

        establishment.setId(1);
        System.out.println(establishment);
        db.establishmentDao().update(establishment);
        EstablishmentLocal e = db.establishmentDao().findById(1);
        List<EstablishmentLocal> establishments = db.establishmentDao().findAll();
        System.out.println(establishments);
        System.out.println(e.toString());

        return "";
    }

    @Override
    public List<EstablishmentLocal> findByName(String name) {
        return db.establishmentDao().findByName(name);
    }
}
