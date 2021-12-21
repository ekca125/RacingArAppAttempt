package com.ekcapaper.racingar.retrofit;

import static org.junit.Assert.*;

import com.ekcapaper.racingar.maptool.MapRange;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

public class MapAddressServiceTest {
    @Test
    public void findAddress() throws IOException{
        Call<String> requester = RetrofitRwabClient.getMapAddressService().findAddress(1);
        boolean result = requester.execute().isSuccessful();
        assertTrue(result);
    }

    @Test
    public void drawRandom() throws IOException {
        Call<String> requester = RetrofitRwabClient.getMapAddressService().drawRandom();
        boolean result = requester.execute().isSuccessful();
        assertTrue(result);
    }

    @Test
    public void drawMapRange() throws IOException{
        double startLatitude = 35.0979529784;
        double startLongitude = 129.0219886069;
        double endLatitude = 35.1066801454;
        double endLongitude = 129.0290353612;

        MapRange mapRange = new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
        Call<String> requester = RetrofitRwabClient.getMapAddressService().drawMapRangeRandom(mapRange);
        boolean result = requester.execute().isSuccessful();
        assertTrue(result);
    }

    @Test
    public void drawMapRangePrint() throws IOException{
        double startLatitude = 35.0979529784;
        double startLongitude = 129.0219886069;
        double endLatitude = 35.1066801454;
        double endLongitude = 129.0290353612;

        MapRange mapRange = new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
        Call<String> requester = RetrofitRwabClient.getMapAddressService().drawMapRangeRandom(mapRange);
        //System.out.println(requester.execute().body());
    }

    @Test
    public void drawMapRangeRandom10() throws IOException{
        double startLatitude = 35.0979529784;
        double startLongitude = 129.0219886069;
        double endLatitude = 35.1066801454;
        double endLongitude = 129.0290353612;

        MapRange mapRange = new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
        Call<String> requester = RetrofitRwabClient.getMapAddressService().drawMapRangeRandom10(mapRange);
        boolean result = requester.execute().isSuccessful();
        assertTrue(result);
    }

    @Test
    public void drawMapRangeRandom50() throws IOException{
        double startLatitude = 35.0979529784;
        double startLongitude = 129.0219886069;
        double endLatitude = 35.1066801454;
        double endLongitude = 129.0290353612;

        MapRange mapRange = new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
        Call<String> requester = RetrofitRwabClient.getMapAddressService().drawMapRangeRandom50(mapRange);
        boolean result = requester.execute().isSuccessful();
        assertTrue(result);
    }

    @Test
    public void drawMapRangeRandom100() throws IOException{
        double startLatitude = 35.0979529784;
        double startLongitude = 129.0219886069;
        double endLatitude = 35.1066801454;
        double endLongitude = 129.0290353612;

        MapRange mapRange = new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
        Call<String> requester = RetrofitRwabClient.getMapAddressService().drawMapRangeRandom100(mapRange);
        boolean result = requester.execute().isSuccessful();
        assertTrue(result);
    }
}