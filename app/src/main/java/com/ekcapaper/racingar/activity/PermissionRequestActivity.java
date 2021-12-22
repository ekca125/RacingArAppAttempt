package com.ekcapaper.racingar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.ekcapaper.racingar.kit.R;
import com.google.android.gms.tasks.OnSuccessListener;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionRequestActivity extends AppCompatActivity {
    final int LOCATION_PERMISSON_REQUEST_CODE = 2;

    String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_request);
        permissionFunction();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @AfterPermissionGranted(LOCATION_PERMISSON_REQUEST_CODE)
    private void permissionFunction(){
        if (EasyPermissions.hasPermissions(this, permissions)) {
            permissionGranted();
        }
        else{
            EasyPermissions.requestPermissions(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION.toString(),
                    LOCATION_PERMISSON_REQUEST_CODE, permissions);
        }
    }

    protected void permissionGranted(){
        Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show();
    }
}