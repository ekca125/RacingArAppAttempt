package com.ekcapaper.racingar.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static String BASE_URL = "https://rawb.ekcapaper.net";
    private RetrofitClient(){}

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static MapAddressService getMapAddressService(){
        return getInstance().create(MapAddressService.class);
    }
}
