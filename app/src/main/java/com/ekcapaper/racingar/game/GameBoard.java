package com.ekcapaper.racingar.game;

import android.location.Location;
import android.util.Log;

import com.ekcapaper.racingar.maptool.MapRange;
import com.ekcapaper.racingar.maptool.MeterToLatitudeConverter;
import com.ekcapaper.racingar.maptool.MeterToLongitudeConverter;

public class GameBoard {
    private final double mapSize;
    private final Location location;
    private final MapRange mapRange;

    /*
        mapsize : kilometer 기준
    */
    public GameBoard(double mapSize, Location location) {
        this.mapSize = mapSize;
        this.location = location;
        this.mapRange = calculateMapRange();
    }

    private MapRange calculateMapRange(){
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        MeterToLatitudeConverter meterToLatitudeConverter = new MeterToLatitudeConverter();
        MeterToLongitudeConverter meterToLongitudeConverter = new MeterToLongitudeConverter(currentLatitude);

        double distanceKilometer = mapSize / 2;

        double halfHeightLatitude = meterToLatitudeConverter.convertKiloMeterToLatitude(distanceKilometer);
        double halfWidthLongitude = meterToLongitudeConverter.convertKilometerToLongitude(distanceKilometer);

        double startLatitude = currentLatitude - halfHeightLatitude;
        double startLongitude = currentLongitude - halfWidthLongitude;
        double endLatitude = currentLatitude + halfHeightLatitude;
        double endLongitude = currentLongitude + halfWidthLongitude;

        return new MapRange(startLatitude, startLongitude, endLatitude, endLongitude);
    }

    public double getMapSize() {
        return mapSize;
    }

    public Location getLocation() {
        return location;
    }

    public MapRange getMapRange() {
        return mapRange;
    }
}
