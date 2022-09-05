package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.LocationsContract;
import com.svalero.toteco_app_aa2.contract.dialog.AddEstablishmentContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Establishment;
import com.svalero.toteco_app_aa2.domain.localdb.EstablishmentLocal;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationsModel implements LocationsContract.Model {

    private TotecoApiInterface api;
    private Context context;

    public LocationsModel(Context context) {
        api = TotecoApi.buildInstance();
        this.context = context;
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
}