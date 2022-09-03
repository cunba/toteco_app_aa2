package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePublicationModel implements DeletePublicationContract.Model {
    private final TotecoApiInterface api;
    private final AppDatabase db;
    private final Context context;

    public DeletePublicationModel(Context context) {
        api = TotecoApi.buildInstance();
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        this.context = context;
    }

    @Override
    public void delete(DeleteListener listener, Publication publication) {
        List<UserLocal> user = db.userDao().findAll();
        String authorization = "Bearer " + user.get(0).getToken();
        Call<String> call = api.deletePublication(authorization, publication.getId());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.deleteError(error);
                    return;
                }
                listener.deleteSuccess(context.getString(R.string.delete_publication_success));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.deleteError(context.getString(R.string.error_database));
            }
        });
    }
}
