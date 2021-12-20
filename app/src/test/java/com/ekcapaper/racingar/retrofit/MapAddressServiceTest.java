package com.ekcapaper.racingar.retrofit;

import static org.junit.Assert.*;

import com.ekcapaper.racingar.maptool.MapRange;
import com.google.gson.Gson;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;

public class MapAddressServiceTest {

    @Test
    public void getMapAddress() throws IOException{
        Double startLatitude = 35.0979529784;
        Double startLongitude = 129.0219886069;
        Double endLatitude = 35.1066801454;
        Double endLongitude = 129.0290353612;

        MapRange mapRange = new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
        Call<String> requester = RetrofitClient.getMapAddressService().getMapAddress(new Gson().toJson(mapRange));
        boolean result = requester.execute().isSuccessful();
        //System.out.println(result);
        assertTrue(result);
    }

    @Test
    public void findAddress() throws IOException{
        Call<String> requester = RetrofitClient.getMapAddressService().findAddress(1);
        boolean result = requester.execute().isSuccessful();
        //System.out.println(result);
        assertTrue(result);
    }

    @Test
    public void testRandomAddress() throws IOException {
        Call<String> requester = RetrofitClient.getMapAddressService().randomAddress();
        boolean result = requester.execute().isSuccessful();
        //System.out.println(result);
        assertTrue(result);
    }

}