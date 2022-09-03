package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.login.JwtRequest;
import com.svalero.toteco_app_aa2.domain.login.JwtResponse;

public interface LoginContract {
    interface Model {
        interface LoginListener {
            void onLoginSuccess(JwtResponse jwtResponse, String password);

            void onLoginError(String error);
        }

        void login(LoginListener listener, JwtRequest jwtRequest);

        interface GetUserLoggedListener {
            void onGetUserLoggedSuccess();

            void onGetUserLoggedError(String error);
        }

        void getUserLogged(GetUserLoggedListener listener, String token, String password);

        boolean isUserLogged();
    }

    interface View {
        void onClickRegister(android.view.View view);

        void login(android.view.View view);

        void showError(String message);

        void onLogin();
    }

    interface Presenter {
        void login(String username, String password);

        void isUserLogged();
    }
}
