package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.LoginContract;
import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.domain.login.JwtRequest;
import com.svalero.toteco_app_aa2.domain.login.JwtResponse;
import com.svalero.toteco_app_aa2.model.LoginModel;
import com.svalero.toteco_app_aa2.view.LoginView;

import java.util.List;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.LoginListener,
        LoginContract.Model.GetUserLoggedListener {

    private final LoginView view;
    private final LoginModel model;

    public LoginPresenter(LoginView view) {
        this.view = view;
        model = new LoginModel(view.getApplicationContext());
    }

    @Override
    public void login(String username, String password) {
        if (username.equals("") || password.equals("")) {
            view.showError(view.getString(R.string.error_field_empty));
            return;
        }

        JwtRequest jwtRequest = new JwtRequest(username, password);
        model.login(this, jwtRequest);
    }

    @Override
    public void onLoginSuccess(JwtResponse jwtResponse) {
        model.getUserLogged(this, jwtResponse.getToken());
    }

    @Override
    public void onLoginError(String error) {
        view.showError(error);
    }

    @Override
    public void onGetUserLoggedSuccess() {
        view.showError("");
        view.onLogin();
    }

    @Override
    public void onGetUserLoggedError(String error) {
        view.showError(error);
    }
}
