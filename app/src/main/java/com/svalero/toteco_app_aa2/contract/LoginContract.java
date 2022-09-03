package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;

import java.util.List;

public interface LoginContract {
    interface Model {
        void createAuxEstablishment();
        void createAuxPublication();
        List<UserLocal> getUser(String username, String password);
    }

    interface View {
        void onClickRegister(android.view.View view);
        void login(android.view.View view);
        void showError(String message);
        void onLogin();
    }

    interface Presenter {
        void login(String username, String password);
        void createAuxEstablishment();
        void createAuxPublication();
    }
}
