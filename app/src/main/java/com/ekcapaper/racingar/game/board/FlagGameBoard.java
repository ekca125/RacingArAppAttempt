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


public class FlagGameBoard extends MultiGameBoard {
    public static class GameFlag {
        //userIdentifier
        String owner;
        @Getter
        Location location;

        public GameFlag(Location location) {
            this.owner = null;
            this.location = location;
        }

        public boolean checkOwned() {
            return owner != null;
        }

        public void setOwner(String userIdentifier) {
            this.owner = userIdentifier;
        }

        public String getOwner() {
            return owner;
        }
    }

    // 50 미터
    private final static double FLAG_GET_DISTANCE = 50;
    private List<GameFlag> gameFlagList;

    public FlagGameBoard(double mapLengthKilometer, Location mapCenter) {
        super(mapLengthKilometer, mapCenter);
    }

    public boolean isDrew() {
        return gameFlagList != null;
    }

    public void drawFlags() {
        if (!isDrew()) {
            try {
                Call<String> call = RetrofitRwabClient
                        .getMapAddressService()
                        .drawMapRangeRandom10(this.getMapRange());
                Response<String> response = call.execute();
                if (response.isSuccessful()) {
                    String body = response.body();
                    Gson gson = new Gson();
                    List<AddressDto> addressDtoList = gson.fromJson(body, new TypeToken<ArrayList<AddressDto>>() {
                    }.getType());
                    gameFlagList = addressDtoList.stream()
                            .map(addressDto -> {
                                Location location = new Location("");
                                location.setLatitude(addressDto.getLatitude());
                                location.setLongitude(addressDto.getLongitude());
                                return new GameFlag(location);
                            })
                            .collect(Collectors.toList());
                }
            } catch (IOException e) {
                e.printStackTrace();
                gameFlagList = null;
            }
        }
    }

    @Override
    public void movePlayer(String userIdentifier, Location location) {
        super.movePlayer(userIdentifier, location);
        for (int i = 0; i < gameFlagList.size(); i++) {
            GameFlag gameFlag = gameFlagList.get(i);
            if (!gameFlag.checkOwned()) {
                // 소유자가 없는 경우
                if (gameFlag.getLocation().distanceTo(location) <= FLAG_GET_DISTANCE) {
                    // 깃발과의 거리가 일정 이하로 떨어진 경우
                    gameFlag.setOwner(userIdentifier);
                }
            }
        }
    }
}


