package com.ekcapaper.racingar.retrofit;

import com.ekcapaper.racingar.maptool.MapRange;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MapAddressService {
    @GET("/api/v1/address/id={id}")
    Call<String> findAddress(@Path("id") long id);

    @GET("/api/v1/address/draw/random")
    Call<String> drawRandom();

    @POST("/api/v1/address/draw/range")
    Call<String> drawMapRangeRandom(@Body MapRange mapRange);
}
