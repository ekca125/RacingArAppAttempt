package com.ekcapaper.racingar.activity.single;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ekcapaper.racingar.activity.EmptyActivity;
import com.ekcapaper.racingar.game.operator.app.GameAppOperator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.data.ThisApplication;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.function.Consumer;

public class SingleGamePrepareActivity extends AppCompatActivity {
    // 액티비티 기능
    private FusedLocationProviderClient fusedLocationProviderClient;
    private GameAppOperator gameAppOperator;
    // ui
    ProgressBar single_game_prepare_progress_indeterminate_circular;
    // 위치 갱신 요청
    LocationRequest locationRequest;
    LocationCallback locationRequestCallback;

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
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(3600);
        locationRequest.setFastestInterval(3600);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationRequestCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    gameAppOperator.makeSingleRoom(location, new Consumer<Void>() {
                        @Override
                        public void accept(Void unused) {
                            // 방을 만든 후에 진행될 내용
                            if (gameAppOperator.checkCurrentGameRoomOperator()) {
                                SingleGamePrepareActivity.this.runOnUiThread(() -> {
                                    fusedLocationProviderClient.removeLocationUpdates(locationRequestCallback);
                                    Intent intent = new Intent(SingleGamePrepareActivity.this, EmptyActivity.class);
                                    startActivity(intent);
                                });
                            }
                        }
                    });
                }

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationRequestCallback,
                Looper.getMainLooper());
    }
}