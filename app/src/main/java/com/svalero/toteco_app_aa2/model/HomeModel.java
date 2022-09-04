package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.HomeContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements HomeContract.Model {

    private final AppDatabase db;
    private final Context context;
    private final TotecoApiInterface api;

    public HomeModel(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        this.context = context;
        api = TotecoApi.buildInstance();
    }

    @Override
    public void loadPublications(LoadPublicationsListener listener) {
        Call<List<Publication>> call = api.getAllPublications();
        call.enqueue(new Callback<List<Publication>>() {
            @Override
            public void onResponse(Call<List<Publication>> call, Response<List<Publication>> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.loadPublicationsError(error);
                    return;
                }

                listener.loadPublicationsSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Publication>> call, Throwable t) {
                t.printStackTrace();
                listener.loadPublicationsError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public void deleteUnsavedLocalProducts() {
        try {
            db.productDao().deleteByPublicationId(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}