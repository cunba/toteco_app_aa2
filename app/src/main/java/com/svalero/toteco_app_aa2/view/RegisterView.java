package com.svalero.toteco_app_aa2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.RegisterContract;
import com.svalero.toteco_app_aa2.domain.dto.RegisterDTO;
import com.svalero.toteco_app_aa2.presenter.RegisterPresenter;

public class RegisterView extends AppCompatActivity implements RegisterContract.View {

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        presenter = new RegisterPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearTexts();
    }

    @Override
    public void register(View view) {
        // We get the values from the text fields
        EditText etUsername = findViewById(R.id.register_username);
        EditText etName = findViewById(R.id.register_name);
        EditText etSurname = findViewById(R.id.register_surname);
        EditText etBirthday = findViewById(R.id.register_birthday);
        EditText etEmail = findViewById(R.id.register_email);
        EditText etPassword = findViewById(R.id.register_password);
        EditText etConfirmPassword = findViewById(R.id.register_confirm_password);

        // Clear focuses and hide keyboard
        etUsername.clearFocus();
        etName.clearFocus();
        etSurname.clearFocus();
        etBirthday.clearFocus();
        etEmail.clearFocus();
        etPassword.clearFocus();
        etConfirmPassword.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etConfirmPassword.getWindowToken(), 0);

        // We convert the values into strings
        String username = etUsername.getText().toString();
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        String birthday = etBirthday.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        RegisterDTO registerDTO = new RegisterDTO(
                username,
                name,
                surname,
                birthday,
                email,
                password,
                confirmPassword
        );

        presenter.register(registerDTO);
    }

    @Override
    public void showError(String message) {
        TextView tvError = findViewById(R.id.register_error);
        tvError.setText(message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegister() {
        Intent intent = new Intent(this, LoginView.class);
        startActivity(intent);
    }

    @Override
    public void clearTexts() {
        EditText etUsername = findViewById(R.id.register_username);
        EditText etName = findViewById(R.id.register_name);
        EditText etSurname = findViewById(R.id.register_surname);
        EditText etBirthday = findViewById(R.id.register_birthday);
        EditText etEmail = findViewById(R.id.register_email);
        EditText etPassword = findViewById(R.id.register_password);
        EditText etConfirmPassword = findViewById(R.id.register_confirm_password);

        etUsername.setText("");
        etName.setText("");
        etSurname.setText("");
        etBirthday.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etConfirmPassword.setText("");
        showError("");
    }
}