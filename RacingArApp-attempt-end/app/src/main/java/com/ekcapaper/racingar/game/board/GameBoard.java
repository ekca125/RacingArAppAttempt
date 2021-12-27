package com.ekcapaper.racingar.game.board;

import android.location.Location;
import android.util.Log;

import com.ekcapaper.racingar.maptool.MapRange;
import com.ekcapaper.racingar.maptool.MeterToLatitudeConverter;
import com.ekcapaper.racingar.maptool.MeterToLongitudeConverter;

import lombok.Getter;

@Getter
public class GameBoard {
    private final double mapLengthKilometer;
    private final MapRange mapRange;
    private final Location mapCenter;

    public GameBoard(double mapLengthKilometer, Location mapCenter) {
        this.mapLengthKilometer = mapLengthKilometer;
        this.mapCenter = mapCenter;
        this.mapRange = calculateMapRange();
    }

    private MapRange calculateMapRange() {
        double currentLatitude = mapCenter.getLatitude();
        double currentLongitude = mapCenter.getLongitude();

        MeterToLatitudeConverter meterToLatitudeConverter = new MeterToLatitudeConverter();
        MeterToLongitudeConverter meterToLongitudeConverter = new MeterToLongitudeConverter(currentLatitude);

        double distanceKilometer = mapLengthKilometer / 2;

        double halfHeightLatitude = meterToLatitudeConverter.convertKiloMeterToLatitude(distanceKilometer);
        double halfWidthLongitude = meterToLongitudeConverter.convertKilometerToLongitude(distanceKilometer);

        double startLatitude = currentLatitude - halfHeightLatitude;
        double startLongitude = currentLongitude - halfWidthLongitude;
        double endLatitude = currentLatitude + halfHeightLatitude;
        double endLongitude = currentLongitude + halfWidthLongitude;

        return new MapRange(startLatitude, startLongitude, endLatitude, endLongitude);
    }
}
