package net.ekcapaper.racingar.components.activity.slider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import net.ekcapaper.racingar.components.R;
import net.ekcapaper.racingar.components.utils.Tools;

public class SliderFilterFlight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_filter_flight);

        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }
}