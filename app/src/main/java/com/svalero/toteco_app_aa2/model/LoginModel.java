package com.svalero.toteco_app_aa2.model;

import android.content.Context;
import android.widget.Toast;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model, LoginContract.Model.LoginListener {

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

                listener.onLoginSuccess(response.body(), jwtRequest.getPassword());
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                t.printStackTrace();
                listener.onLoginError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public void getUserLogged(GetUserLoggedListener listener, String token, String password) {
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

                UserLocal userLocal = new UserLocal(response.body());
                userLocal.setPassword(password);
                userLocal.setToken(token);
                db.userDao().insert(userLocal);
                listener.onGetUserLoggedSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                listener.onGetUserLoggedError(context.getString(R.string.error_database));
            }
        });
    }

    @Override
    public boolean isUserLogged() {
        List<UserLocal> user = db.userDao().findAll();
        if (!user.isEmpty()) {
            JwtRequest jwtRequest = new JwtRequest(user.get(0).getUsername(), user.get(0).getPassword());
            login(this, jwtRequest);
            return true;
        }
        return false;
    }

    @Override
    public void onLoginSuccess(JwtResponse jwtResponse, String password) {
        // Refresh token
        List<UserLocal> user = db.userDao().findAll();
        UserLocal userLocal = user.get(0);
        userLocal.setToken(jwtResponse.getToken());
        db.userDao().update(userLocal);
    }

    @Override
    public void onLoginError(String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }
}
