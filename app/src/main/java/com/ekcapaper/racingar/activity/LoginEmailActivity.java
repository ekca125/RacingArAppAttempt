package com.ekcapaper.racingar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ekcapaper.racingar.game.GameAppOperator;
import com.google.android.material.textfield.TextInputEditText;
import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.data.ThisApplication;

import java.util.concurrent.ExecutionException;

public class LoginEmailActivity extends AppCompatActivity {
    GameAppOperator gameAppOperator;

    TextInputEditText text_input_login_email;
    TextInputEditText text_input_login_password;
    TextView text_view_email_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);

        gameAppOperator = ((ThisApplication)getApplication()).getGameAppOperator();

        text_input_login_email = findViewById(R.id.text_input_login_email);
        text_input_login_password = findViewById(R.id.text_input_login_password);
        text_view_email_sign_in = findViewById(R.id.text_view_email_sign_in);

        stubLogin();

        text_view_email_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = text_input_login_email.getText().toString();
                String password = text_input_login_password.getText().toString();
                requestLogin(email, password);
            }
        });
    }

    private void requestLogin(String email, String password){
        try {
            gameAppOperator.loginEmail(email, password);
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginEmailActivity.this, GameSelectionActivity.class);
            startActivity(intent);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
        }
    }

    private void stubLogin(){
        text_input_login_email.setText("abcd@example.com");
        text_input_login_password.setText("abcd1234");
    }
}