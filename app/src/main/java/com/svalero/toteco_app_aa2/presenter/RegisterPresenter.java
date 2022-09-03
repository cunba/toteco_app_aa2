package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.RegisterContract;
import com.svalero.toteco_app_aa2.domain.localdb.UserLocal;
import com.svalero.toteco_app_aa2.domain.dto.RegisterDTO;
import com.svalero.toteco_app_aa2.model.RegisterModel;
import com.svalero.toteco_app_aa2.view.RegisterView;

import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter {

    private final RegisterView view;
    private final RegisterModel model;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
        model = new RegisterModel(view.getApplicationContext());
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        if (registerDTO.getUsername().equals("") ||
                registerDTO.getName().equals("") ||
                registerDTO.getSurname().equals("") ||
                registerDTO.getBirthday().equals("") ||
                registerDTO.getPassword().equals("") ||
                registerDTO.getConfirmPassword().equals("")) {

            view.showError(view.getString(R.string.error_field_empty));
            return;
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            view.showError(view.getString(R.string.error_password));
            return;
        }

        List<UserLocal> userLocal = model.getUser(registerDTO.getUsername());
        if (userLocal.size() != 0) {
            view.showError(view.getString(R.string.error_username, registerDTO.getUsername()));
            return;
        }

        view.showError("");

        // If there is not errors we create the user
        UserLocal newUserLocal = new UserLocal(
//                registerDTO.getUsername(),
//                registerDTO.getName(),
//                registerDTO.getSurname(),
//                registerDTO.getBirthday(),
//                registerDTO.getPassword()
        );

        model.register(newUserLocal);
        view.showToast(view.getString(R.string.user_create, registerDTO.getUsername()));
        view.clearTexts();
        view.onRegister();
    }
}
