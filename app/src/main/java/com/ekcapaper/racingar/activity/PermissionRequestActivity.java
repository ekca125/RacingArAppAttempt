package com.ekcapaper.racingar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.ekcapaper.racingar.kit.R;
import com.google.android.gms.tasks.OnSuccessListener;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionRequestActivity extends AppCompatActivity {
    final int LOCATION_PERMISSON_REQUEST_CODE = 2;

    String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);

        if (EasyPermissions.hasPermissions(this, permissions)) {
            // 권한 체크 2
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
        else{
            // 권한이 없는 경우
            permissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @AfterPermissionGranted(LOCATION_PERMISSON_REQUEST_CODE)
    private void permissionGranted(){
        // 권한 체크
        if (EasyPermissions.hasPermissions(this, permissions)) {
            // 권한 체크 2
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
        else{
            // 권한이 없는 경우
            EasyPermissions.requestPermissions(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION.toString(),
                    LOCATION_PERMISSON_REQUEST_CODE, permissions);
        }
    }
}