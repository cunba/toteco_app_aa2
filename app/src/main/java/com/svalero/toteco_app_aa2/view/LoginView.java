package com.svalero.toteco_app_aa2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.svalero.toteco_app_aa2.R;
import com.svalero.toteco_app_aa2.contract.LoginContract;
import com.svalero.toteco_app_aa2.presenter.LoginPresenter;

public class LoginView extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showError("");
        presenter.createAuxEstablishment();
        presenter.createAuxPublication();
    }

    @Override
    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterView.class);
        startActivity(intent);
    }

    @Override
    public void onLogin() {
        Intent intent = new Intent(this, MainView.class);
        startActivity(intent);
    }

    @Override
    public void login(View view) {
        showError("");
        // We get the values from the texts
        EditText etUsername = findViewById(R.id.main_username);
        EditText etPassword = findViewById(R.id.main_password);
        TextView tvError = findViewById(R.id.main_error);

        // Remove focus from edit texts and hide the keyboard
        etUsername.clearFocus();
        etPassword.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etPassword.getWindowToken(), 0);

        // We convert the values into strings
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        presenter.login(username, password);
    }

    @Override
    public void showError(String message) {
        TextView tvError = findViewById(R.id.main_error);
        tvError.setText(message);
    }

    @Override
    public void onBackPressed() {

    }
}