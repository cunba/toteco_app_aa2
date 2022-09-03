package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.RegisterContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.domain.dto.UserDTO;
import com.svalero.toteco_app_aa2.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterModel implements RegisterContract.Model {

    private final AppDatabase db;
    private final Context context;

    public RegisterModel(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    @Override
    public void register(OnRegisterListener listener, UserDTO userDTO) {
        TotecoApiInterface api = TotecoApi.buildInstance();
        Call<User> call = api.createUser(userDTO);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.onRegisterError(error);
                    return;
                }
                User user = response.body();
                listener.onRegisterSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onRegisterError(context.getString(R.string.error_database));
            }
        });
    }
}
