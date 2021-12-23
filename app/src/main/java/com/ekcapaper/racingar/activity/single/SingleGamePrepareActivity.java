package com.ekcapaper.racingar.activity.single;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ekcapaper.racingar.game.operator.app.GameAppOperator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.data.ThisApplication;

public class SingleGamePrepareActivity extends AppCompatActivity {
    // 액티비티 기능
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GameAppOperator gameAppOperator;
    // ui
    ProgressBar single_game_prepare_progress_indeterminate_circular;
    //
    LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_prepare);
        // 액티비티 기능
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        gameAppOperator = ((ThisApplication) getApplication()).getGameAppOperator();
        // ui
        single_game_prepare_progress_indeterminate_circular =
                findViewById(R.id.single_game_prepare_progress_indeterminate_circular);

        runProgressDeterminateCircular();
        runPrepareProgress();
    }

    private void runProgressDeterminateCircular() {
        final Handler mHandler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                int progress = single_game_prepare_progress_indeterminate_circular.getProgress() + 10;
                single_game_prepare_progress_indeterminate_circular.setProgress(progress);
                if (progress > 100) {
                    single_game_prepare_progress_indeterminate_circular.setProgress(0);
                }
                mHandler.postDelayed(this, 500);
            }
        };
        mHandler.post(runnable);
    }

    private void runPrepareProgress() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    gameAppOperator.makeSingleRoom(location);
                    if (gameAppOperator.checkCurrentGameRoomOperator()) {
                        Toast.makeText(SingleGamePrepareActivity.this, "Start Game", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SingleGamePrepareActivity.this, "Make Room Failed", Toast.LENGTH_SHORT).show();
                    }
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback);
                    // next activity
                    Intent intent = new Intent(SingleGamePrepareActivity.this, SingleGameMapActivity.class);
                    startActivity(intent);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }
}