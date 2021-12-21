package com.ekcapaper.racingar.components.activity.settings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.ekcapaper.racingar.components.R;
import com.ekcapaper.racingar.components.utils.Tools;

public class SettingFlat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_flat);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }

}
