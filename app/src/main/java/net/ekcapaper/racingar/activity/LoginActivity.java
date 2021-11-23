package net.ekcapaper.racingar.activity;

import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.activity.login.LoginCardLight;
import net.ekcapaper.racingar.components.data.ThisApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends LoginCardLight {
    ExecutorService executorService;

    TextInputEditText loginInputEmail;
    TextInputEditText loginInputPassword;
    MaterialRippleLayout loginLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.executorService = Executors.newSingleThreadExecutor();

        this.loginInputEmail = findViewById(R.id.login_input_email);
        this.loginInputPassword = findViewById(R.id.login_input_password);
        this.loginLayout = findViewById(R.id.login_layout);
        this.loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.execute(() -> {
                    String email = loginInputEmail.toString();
                    String password = loginInputPassword.toString();

                    ThisApplication thisApplication = (ThisApplication) (LoginActivity.this.getApplication());
                    boolean result = thisApplication.getGameAppOperator().authEmail(email, password);
                    if (result) {
                        runOnUiThread(() -> {
                            Snackbar.make(parent_view, "Login Success", Snackbar.LENGTH_SHORT).show();
                        });
                    } else {
                        runOnUiThread(() -> {
                            Snackbar.make(parent_view, "Login Fail", Snackbar.LENGTH_SHORT).show();
                        });
                    }
                });
            }
        });
        this.loginInputEmail.setText("abcd@gmail.com");
        this.loginInputPassword.setText("abcd1234");
    }
}
