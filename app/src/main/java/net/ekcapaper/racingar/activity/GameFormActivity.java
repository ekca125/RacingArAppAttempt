package net.ekcapaper.racingar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.internal.location.zzas;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;

import net.ekcapaper.racingar.components.R;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameFormActivity extends AppCompatActivity {
    TextInputEditText textview_gameform_date;
    TextInputEditText textview_gameform_latitude;
    TextInputEditText textview_gameform_longitude;
    TextInputEditText textview_gameform_range;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameform);

        LocalDateTime localDateTime = LocalDateTime.now();

        textview_gameform_range.setText("10");
    }
}