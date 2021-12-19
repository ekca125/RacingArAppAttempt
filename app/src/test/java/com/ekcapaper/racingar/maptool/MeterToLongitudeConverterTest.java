package com.ekcapaper.racingar.maptool;

import static org.junit.Assert.*;

import org.junit.Test;

public class MeterToLongitudeConverterTest {

    @Test
    public void convertMeterToLongitude() {
        MeterToLongitudeConverter meterToLongitudeConverter = new MeterToLongitudeConverter(37);
        double longitude = meterToLongitudeConverter.convertMeterToLongitude(1);
        assertTrue(longitude <= 40);
        assertTrue(longitude >= 20);
    }
}