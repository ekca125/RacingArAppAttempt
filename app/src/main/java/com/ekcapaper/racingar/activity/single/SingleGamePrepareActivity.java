package com.ekcapaper.racingar.activity.single;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ekcapaper.racingar.game.GameAppOperator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.data.ThisApplication;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SingleGamePrepareActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationClient;
    GameAppOperator gameAppOperator;
    ProgressBar single_game_prepare_progress_indeterminate_circular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_prepare);

        single_game_prepare_progress_indeterminate_circular =
                findViewById(R.id.single_game_prepare_progress_indeterminate_circular);

        gameAppOperator = ((ThisApplication) getApplication()).getGameAppOperator();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        runProgressDeterminateCircular();
        runPrepareProgress();
    }

    private void runPrepareProgress() {
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
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // 위치를 가져오는데 성공하면 방을 만드는 콜백 실행
                        runMakeRoomProgress(location);
                    }
                });
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
    }
}