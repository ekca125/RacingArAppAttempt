package com.ekcapaper.racingar.activity.single;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ekcapaper.racingar.game.operator.GameAppOperator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.data.ThisApplication;

public class SingleGamePrepareActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationClient;
    GameAppOperator gameAppOperator;
    ProgressBar single_game_prepare_progress_indeterminate_circular;

    private FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_prepare);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        single_game_prepare_progress_indeterminate_circular =
                findViewById(R.id.single_game_prepare_progress_indeterminate_circular);

        gameAppOperator = ((ThisApplication) getApplication()).getGameAppOperator();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        runProgressDeterminateCircular();
        runPrepareProgress();
    }

    private void runPrepareProgress() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // 1초 마다
        locationRequest.setInterval(1000);
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                runMakeRoomProgress(locationResult.getLastLocation());
            }
        };
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

    private void runMakeRoomProgress(Location location) {
        // 위치를 전달하여 방을 생성하기
        gameAppOperator.makeSingleRoom(location);
        if(gameAppOperator.checkCurrentGameRoomOperator()){
            Toast.makeText(this,"Start Game", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Make Room Failed",Toast.LENGTH_SHORT).show();
        }
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}