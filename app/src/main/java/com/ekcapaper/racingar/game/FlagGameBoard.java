package com.ekcapaper.racingar.game;

import android.location.Location;
import android.util.Log;

import com.ekcapaper.racingar.dto.AddressDto;
import com.ekcapaper.racingar.maptool.MapRange;
import com.ekcapaper.racingar.maptool.MeterToLatitudeConverter;
import com.ekcapaper.racingar.maptool.MeterToLongitudeConverter;
import com.ekcapaper.racingar.retrofit.RetrofitRwabClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class FlagGameBoard extends GameBoard{
    private List<AddressDto> addressDtoList;
    private Location location;

    public FlagGameBoard(double mapSize, Location location) {
        super(mapSize, location);
        addressDtoList = null;
    }

    public List<AddressDto> getAddressDtoList() {
        return addressDtoList;
    }

    public boolean isDrew(){
        return addressDtoList != null;
    }

    public void drawFlags(){
        if(!isDrew()){
            try {
                Call<String> call = RetrofitRwabClient
                        .getMapAddressService()
                        .drawMapRangeRandom10(this.getMapRange());
                Response<String> response = call.execute();
                if(response.isSuccessful()){
                    String body = response.body();
                    Gson gson = new Gson();
                    addressDtoList = gson.fromJson(body, new TypeToken<ArrayList<AddressDto>>(){}.getType());
                }
            } catch (IOException e) {
                e.printStackTrace();
                addressDtoList = null;
            }
        }
    }

    public void movePlayer(Location location){
        this.location = location;
    }
}


