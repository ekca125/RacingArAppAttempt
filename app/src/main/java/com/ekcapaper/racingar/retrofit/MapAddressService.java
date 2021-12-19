package com.ekcapaper.racingar.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MapAddressService {
    @POST("/api/v1/address/range-random")
    Call<String> getMapAddress(@Body String json);
}
