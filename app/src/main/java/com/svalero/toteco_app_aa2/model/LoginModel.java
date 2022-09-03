package com.svalero.toteco_app_aa2.model;

import android.content.Context;

import androidx.room.Room;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.api.TotecoApi;
import com.svalero.toteco_app_aa2.api.TotecoApiInterface;
import com.svalero.toteco_app_aa2.contract.LoginContract;
import com.svalero.toteco_app_aa2.database.AppDatabase;
import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.domain.login.JwtRequest;
import com.svalero.toteco_app_aa2.domain.login.JwtResponse;
import com.svalero.toteco_app_aa2.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    private final AppDatabase db;
    private final TotecoApiInterface api;
    private final Context context;

    public LoginModel(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, "toteco")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        api = TotecoApi.buildInstance();
    }

    @Override
    public void login(LoginListener listener, JwtRequest jwtRequest) {
        Call<JwtResponse> call = api.login(jwtRequest);
        call.enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.onLoginError(error);
                    return;
                }

                JwtResponse jwtResponse = response.body();
                listener.onLoginSuccess(jwtResponse);
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                listener.onLoginError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public void getUserLogged(GetUserLoggedListener listener, String token) {
        String authorization = "Bearer " + token;
        Call<User> call = api.getUserLoged(authorization);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    String error = Utils.getErrorResponse(response.errorBody().charStream());
                    listener.onGetUserLoggedError(error);
                    return;
                }

                User user = response.body();
                System.out.println(user.toString());
                UserLocal userLocal = new UserLocal(user);
                userLocal.setToken(token);
                db.userDao().insert(userLocal);
                listener.onGetUserLoggedSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getCause());
                System.out.println(t.getMessage());
                listener.onGetUserLoggedError(context.getString(R.string.error_database));
            }
        });
    }
}
