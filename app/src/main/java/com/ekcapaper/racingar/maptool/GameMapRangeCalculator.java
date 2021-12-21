package com.ekcapaper.racingar.maptool;

import android.location.Location;

public class GameMapRangeCalculator {
    private double mapSize;
    public GameMapRangeCalculator(){
        this.mapSize = 1.0;
    }

    public GameMapRangeCalculator(double mapSize){
        this.mapSize = mapSize;
    }

    public MapRange getMapRange(Location location) {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        MeterToLatitudeConverter meterToLatitudeConverter = new MeterToLatitudeConverter();
        MeterToLongitudeConverter meterToLongitudeConverter = new MeterToLongitudeConverter(currentLatitude);

        double distanceMeter = mapSize / 2;

        double startLatitude = currentLatitude - meterToLatitudeConverter.convertMeterToLatitude(distanceMeter);
        double startLongitude = currentLongitude - meterToLongitudeConverter.convertMeterToLongitude(distanceMeter);
        double endLatitude = currentLatitude + meterToLatitudeConverter.convertMeterToLatitude(distanceMeter);
        double endLongitude = currentLongitude + meterToLongitudeConverter.convertMeterToLongitude(distanceMeter);

        return new MapRange(startLatitude, startLongitude, endLatitude, endLongitude);
    }
}
