package com.ekcapaper.racingar.maptool;

import static org.junit.Assert.*;

import org.junit.Test;

public class MeterToLatitudeConverterTest {
    @Test
    public void convertMeterToLatitude() {
        MeterToLatitudeConverter meterToLatitudeConverter = new MeterToLatitudeConverter();
        double latitude = meterToLatitudeConverter.convertMeterToLatitude(1);
        assertTrue(latitude <= 40);
        assertTrue(latitude >= 20);
    }
}