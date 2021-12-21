package com.ekcapaper.material.components.activity.verification;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.ekcapaper.material.components.R;
import com.ekcapaper.material.components.utils.Tools;

public class VerificationPhone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);
        Tools.setSystemBarColor(this, R.color.grey_20);
    }
}
