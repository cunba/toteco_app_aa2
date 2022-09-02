package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.svalero.toteco_app_aa2.contract.LocationsContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;

import java.util.List;

public class LocationsModel implements LocationsContract.Model {

    private final AppDatabase db;

    public LocationsModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
    }

    @Override
    public List<Establishment> loadEstablishments() {
        try {
            return db.establishmentDao().findAllExceptAux();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}