package com.ekcapaper.racingar.game.board;

import android.location.Location;

import com.ekcapaper.racingar.dto.AddressDto;
import com.ekcapaper.racingar.retrofit.RetrofitRwabClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Response;


public abstract class FlagGameBoard extends GameBoard{
    protected List<GameFlag> gameFlagList;

    public FlagGameBoard(double mapSize, Location location) {
        super(mapSize, location);
        gameFlagList = null;
    }

    public boolean isDrew(){
        return gameFlagList != null;
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
                    List<AddressDto> addressDtoList = gson.fromJson(body, new TypeToken<ArrayList<AddressDto>>(){}.getType());
                    gameFlagList = addressDtoList.stream()
                            .map(new Function<AddressDto, GameFlag>() {
                                @Override
                                public GameFlag apply(AddressDto addressDto) {
                                    return new GameFlag(addressDto);
                                }
                            })
                            .collect(Collectors.toList());
                }
            } catch (IOException e) {
                e.printStackTrace();
                gameFlagList = null;
            }
        }
    }

    abstract public void movePlayer(Location location);
}


