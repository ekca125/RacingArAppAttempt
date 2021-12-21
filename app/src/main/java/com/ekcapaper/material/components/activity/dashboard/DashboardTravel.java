package com.ekcapaper.material.components.activity.dashboard;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.ekcapaper.material.components.R;
import com.ekcapaper.material.components.utils.Tools;

public class DashboardTravel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_travel);
        initToolbar();
    }

    private void initToolbar() {
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }
}
