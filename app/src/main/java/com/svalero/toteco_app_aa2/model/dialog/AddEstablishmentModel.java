package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.google.android.gms.maps.model.Marker;
import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.dialog.AddEstablishmentContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.dto.view.AddEstablishmentDTO;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEstablishmentModel implements AddEstablishmentContract.Model {
    private final Context context;
    private final TotecoApiInterface api;
    private final AppDatabase db;

    public AddEstablishmentModel(Context context) {
        this.context = context;
        api = TotecoApi.buildInstance();
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public void loadEstablishments(LoadEstablishmentsListener listener) {
        Call<List<Establishment>> call = api.getAllEstablishments();
        call.enqueue(new Callback<List<Establishment>>() {
            @Override
            public void onResponse(Call<List<Establishment>> call, Response<List<Establishment>> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.loadEstablishmentsError(error);
                    return;
                }

                listener.loadEstablishmentsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Establishment>> call, Throwable t) {
                t.printStackTrace();
                listener.loadEstablishmentsError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public String onPressSubmit(AddEstablishmentDTO addEstablishmentDTO) {
        if (addEstablishmentDTO.getName().equals("")) {
            return context.getString(R.string.error_field_empty);
        }
        if (addEstablishmentDTO.getPunctuation().equals("")) {
            return context.getString(R.string.error_field_empty);
        }

        Establishment establishment = addEstablishmentDTO.getEstablishment();
        float punctuation = Float.parseFloat(addEstablishmentDTO.getPunctuation());
        if (punctuation > 5) {
            return context.getString(R.string.add_product_error_punctuation);
        }

        establishment.setPunctuation(punctuation);
        EstablishmentLocal e;

        if (establishment.getId() < 100) {
            establishment.setName(addEstablishmentDTO.getName());
            e = new EstablishmentLocal(establishment);
            e.setId(100 + establishment.getId());
            db.establishmentDao().insert(e);
        } else {
            e = new EstablishmentLocal(establishment);
            db.establishmentDao().update(e);
        }

        return "";
    }

    @Override
    public Establishment findEstablishment(List<Establishment> establishments, Marker marker) {
        Predicate<Establishment> byLatitude = e -> e.getLocation().getLatitude() == marker.getPosition().latitude;
        Predicate<Establishment> byLongitude = e -> e.getLocation().getLongitude() == marker.getPosition().longitude;
        Predicate<Establishment> byName = e -> e.getName().equals(marker.getTitle());

        List<Establishment> establishment = establishments.stream().filter(byLongitude)
                .filter(byLatitude).filter(byName).collect(Collectors.toList());
        return establishment.get(0);
    }
}
