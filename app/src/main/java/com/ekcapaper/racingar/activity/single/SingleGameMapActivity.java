package com.ekcapaper.racingar.activity.single;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ekcapaper.racingar.game.board.GameFlag;
import com.ekcapaper.racingar.game.message.MessageOpCodeStorage;
import com.ekcapaper.racingar.game.message.MovePlayerMessage;
import com.ekcapaper.racingar.game.operator.GameAppOperator;
import com.ekcapaper.racingar.game.operator.SingleGameRoomOperator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.ekcapaper.racingar.kit.R;
import com.ekcapaper.racingar.kit.data.ThisApplication;
import com.ekcapaper.racingar.kit.utils.Tools;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.heroiclabs.nakama.AbstractSocketListener;
import com.heroiclabs.nakama.MatchData;
import com.heroiclabs.nakama.SocketListener;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SingleGameMapActivity extends AppCompatActivity {
    private GameAppOperator gameAppOperator;
    private SingleGameRoomOperator singleGameRoomOperator;

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game_map);

        gameAppOperator = ((ThisApplication)getApplicationContext()).getGameAppOperator();
        if(gameAppOperator.checkCurrentGameRoomOperator()){
            singleGameRoomOperator = (SingleGameRoomOperator) gameAppOperator.getCurrentGameRoomOperator();
        }
        else{
            // 없는 경우에는 종료
            Toast.makeText(this,"오류 : 정상적인 접근이 아닙니다.",Toast.LENGTH_SHORT).show();
            finish();
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        singleGameRoomOperator.sendPlayerMoveMessage(location);
                    }
                }
            }

            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
            }
        };

        initMapFragment();
        Tools.setSystemBarColor(this, R.color.colorPrimary);

        initFusedLocation();

        // start game
        singleGameRoomOperator.setAfterPlayerMoveCallback(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                refreshScreenMap();
            }
        });
        singleGameRoomOperator.startReceiveMessageCallback();
    }

    Marker marker = null;
    // marker
    private void refreshScreenMap(){
        List<GameFlag> gameFlagList = singleGameRoomOperator.getFlagSingleGameBoard().getGameFlagList();
        List<GameFlag> reminderGameFlagList = gameFlagList.stream()
                .filter(new Predicate<GameFlag>() {
                    @Override
                    public boolean test(GameFlag gameFlag) {
                        return !gameFlag.checkOwned();
                    }
                })
                .collect(Collectors.toList());

        Location playerLocation = singleGameRoomOperator.getFlagSingleGameBoard().getCurrentPlayerLocation();
        LatLng playerLatlng = new LatLng(playerLocation.getLatitude(),playerLocation.getLongitude());

        // player location
        if(marker != null){
            marker.remove();
            marker = null;
        }

        marker = mMap.addMarker(new MarkerOptions()
                .position(playerLatlng)
                .title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(playerLatlng));

        // flags

    }

    private void initMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_single_game);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = Tools.configActivityMaps(googleMap);
                mMap.moveCamera(zoomingLocation());
            }
        });
    }

    private CameraUpdate zoomingLocation() {
        return CameraUpdateFactory.newLatLngZoom(new LatLng(37.541, 126.986), 13);
    }

    private void initFusedLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        startLocationCallback();
    }

    private void startLocationCallback(){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // 1초 마다
        locationRequest.setInterval(1000);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 권한이 없다면 종료
            finish();
        }
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    private void stopLocationCallback(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationCallback();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationCallback();
    }

    @Override
    protected void onStop() {
        super.onStop();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        fusedLocationProviderClient = null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(fusedLocationProviderClient == null){
            initFusedLocation();
        }
    }

    public void clickAction(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.map_button:
                Toast.makeText(getApplicationContext(), "Map Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.list_button:
                Toast.makeText(getApplicationContext(), "List Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_button:
                Toast.makeText(getApplicationContext(), "Add Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}