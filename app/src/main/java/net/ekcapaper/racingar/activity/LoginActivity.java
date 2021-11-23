package net.ekcapaper.racingar.activity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.activity.login.LoginCardLight;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends LoginCardLight {
    ExecutorService executorService;

    TextInputEditText loginInputEmail;
    TextInputEditText loginInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.executorService = Executors.newSingleThreadExecutor();

        this.loginInputEmail = findViewById(R.id.login_input_email);
        this.loginInputPassword = findViewById(R.id.login_input_password);

    }
}
