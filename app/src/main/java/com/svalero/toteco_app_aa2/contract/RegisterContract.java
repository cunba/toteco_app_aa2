package com.svalero.toteco_app_aa2.contract;

import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.domain.dto.view.RegisterDTO;
import com.svalero.toteco_app_aa2.domain.dto.UserDTO;

public interface RegisterContract {

    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(User user);
            void onRegisterError(String error);
        }
        void register(OnRegisterListener listener, UserDTO userDTO);
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
