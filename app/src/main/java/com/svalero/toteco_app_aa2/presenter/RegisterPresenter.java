package com.svalero.toteco_app_aa2.presenter;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.RegisterContract;
import com.svalero.toteco_app_aa2.domain.User;
import com.svalero.toteco_app_aa2.domain.dto.RegisterDTO;
import com.svalero.toteco_app_aa2.domain.dto.UserDTO;
import com.svalero.toteco_app_aa2.model.RegisterModel;
import com.svalero.toteco_app_aa2.view.RegisterView;

import java.util.List;

public class RegisterPresenter implements RegisterContract.Presenter, RegisterContract.Model.OnRegisterListener {

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
                registerDTO.getEmail().equals("") ||
                registerDTO.getPassword().equals("") ||
                registerDTO.getConfirmPassword().equals("")) {

            view.showError(view.getString(R.string.error_field_empty));
            return;
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            view.showError(view.getString(R.string.error_password));
            return;
        }

//        List<User> user = model.getUser(registerDTO.getUsername());
//        if (user.isEmpty()) {
//            view.showError(view.getString(R.string.error_username, registerDTO.getUsername()));
//            return;
//        }

        view.showError("");

        // If there is not errors we create the user
        UserDTO userDTO = new UserDTO(
                registerDTO.getUsername(),
                registerDTO.getName(),
                registerDTO.getSurname(),
                registerDTO.getBirthday(),
                registerDTO.getEmail(),
                registerDTO.getPassword(),
                "USER"
        );

        model.register(this, userDTO);
    }


    @Override
    public void onRegisterSuccess(User user) {
        view.showToast(view.getString(R.string.user_create, user.getUsername()));
        view.clearTexts();
        view.onRegister();
    }

    @Override
    public void onRegisterError(String error) {
        view.showError(error);
    }
}
