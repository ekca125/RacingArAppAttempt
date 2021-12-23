package com.ekcapaper.racingar.game.board;

import android.location.Location;

import com.ekcapaper.racingar.dto.AddressDto;
import com.ekcapaper.racingar.game.message.MovePlayerMessage;
import com.ekcapaper.racingar.retrofit.RetrofitRwabClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Response;


public abstract class FlagGameBoard extends GameBoard{
    public class GameFlag extends AddressDto{
        @Setter
        @Getter
        String owner;
        //userIdentifier
        @Builder
        public GameFlag(long id, double latitude, double longitude, String owner) {
            super(id, latitude, longitude);
            this.owner = owner;
        }
    }



    public class GameFlag extends AddressDto {
        @Setter
        @Getter
        String owner;

        public GameFlag(long id, double latitude, double longitude) {
            super(id, latitude, longitude);
            owner = null;
        }

        public GameFlag(AddressDto addressDto){
            super(addressDto.getId(),addressDto.getLatitude(),addressDto.getLongitude());
            owner = null;
        }

        public boolean checkOwned(){
            return owner != null;
        }
    }

    protected List<GameFlag> gameFlagList;

    public FlagGameBoard(double mapSize, Location location) {
        super(mapSize, location);
        gameFlagList = null;
    }

    public List<GameFlag> getGameFlagList() {
        return gameFlagList;
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

    // 50 미터
    private final static double FLAG_GET_DISTANCE = 50;
    public FlagSingleGameBoard(double mapSize, Location location) {
        super(mapSize, location);
    }

    @Override
    public void movePlayer(MovePlayerMessage movePlayerMessage) {
        Location playerLocation = new Location("");
        playerLocation.setLatitude(movePlayerMessage.getLatitude());
        playerLocation.setLongitude(movePlayerMessage.getLongitude());

        this.currentPlayerLocation = playerLocation;
        for(int i=0; i<gameFlagList.size();i++){
            GameFlag gameFlag = gameFlagList.get(i);
            if(!gameFlag.checkOwned()){
                Location address1 = new Location("");
                address1.setLatitude(gameFlag.getLatitude());
                address1.setLongitude(gameFlag.getLongitude());

                Location address2 = currentPlayerLocation;

                double distance = address1.distanceTo(address2);
                if(distance < 0){
                    distance = -distance;
                }

                if(distance <= FLAG_GET_DISTANCE){
                    gameFlag.setOwner(movePlayerMessage.getUserIdentifier());
                }
            }
        }
    }

    abstract public void movePlayer(MovePlayerMessage movePlayerMessage);
}


