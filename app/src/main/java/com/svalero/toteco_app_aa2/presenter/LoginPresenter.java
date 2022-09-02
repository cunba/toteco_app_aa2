package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.LoginContract;
import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.model.LoginModel;
import com.svalero.toteco_app_aa2.view.LoginView;

import java.util.List;

public class LoginPresenter implements LoginContract.Presenter {

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
        } else {
            // Get the user
            List<User> user = model.getUser(username, password);

            // If the list is empty means that the user with this username and password doesn't exists
            if (user.size() == 0) {
                view.showError(view.getString(R.string.error_user));
            } else {
                view.showError("");
                view.onLogin();
            }
        }
    }

    @Override
    public void createAuxEstablishment() {
        model.createAuxEstablishment();
    }

    @Override
    public void createAuxPublication() {
        model.createAuxPublication();
    }

}
