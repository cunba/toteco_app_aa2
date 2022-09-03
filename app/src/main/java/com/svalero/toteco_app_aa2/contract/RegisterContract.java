package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.domain.dto.RegisterDTO;

import java.util.List;

public interface RegisterContract {

    interface Model {
        void register(UserLocal userLocal);
        List<UserLocal> getUser(String username);
    }

    interface View {
        void register(android.view.View view);
        void showError(String message);
        void showToast(String message);
        void onRegister();
        void clearTexts();
    }

    interface Presenter {
        void register(RegisterDTO registerDTO);
    }
}
