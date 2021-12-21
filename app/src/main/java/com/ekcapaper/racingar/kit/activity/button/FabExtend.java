package com.ekcapaper.racingar.kit.activity.button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.utils.Tools;

public class FabExtend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_extend);

        Tools.setSystemBarLight(this);
    }
}