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
    @POST("/api/v1/address/range-random")
    Call<String> getMapAddress(@Body String jsonMapRange);

    @GET("/api/v1/address/id={id}")
    Call<String> findAddress(@Path("id") long id);

    @GET("/api/v1/address/random")
    Call<String> randomAddress();
}
