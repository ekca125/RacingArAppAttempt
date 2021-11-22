package net.ekcapaper.racingar.components.activity.button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.utils.Tools;

public class FabExtend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_extend);

        Tools.setSystemBarLight(this);
    }
}