package com.svalero.toteco_app_aa2.model.dialog;

import android.content.Context;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.dialog.DeletePublicationContract;
import com.svalero.toteco_app_aa2.domain.Publication;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePublicationModel implements DeletePublicationContract.Model {
    private final TotecoApiInterface api;
    private final Context context;

    public DeletePublicationModel(Context context) {
        api = TotecoApi.buildInstance();
        this.context = context;
    }

    @Override
    public void delete(DeleteListener listener, Publication publication) {
        UserLocal user = Utils.getUserLogged(context);
        String authorization = "Bearer " + user.getToken();
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
                t.printStackTrace();
                listener.deleteError(context.getString(R.string.error_database));
            }
        });
    }
}
