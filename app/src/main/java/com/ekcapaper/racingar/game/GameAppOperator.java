package com.ekcapaper.racingar.game;

import android.location.Location;

import com.ekcapaper.racingar.dto.AddressDto;
import com.ekcapaper.racingar.maptool.GameMapRangeCalculator;
import com.ekcapaper.racingar.maptool.MapRange;
import com.ekcapaper.racingar.nakama.NakamaNetworkManager;
import com.ekcapaper.racingar.retrofit.RetrofitRwabClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import retrofit2.Call;

public class GameAppOperator extends NakamaNetworkManager {
    private GameRoomOperator currentGameRoomOperator;

    public GameAppOperator() {
        super();
    }

    public boolean checkCurrentGameRoomOperator(){
        return currentGameRoomOperator != null;
    }

    public void leaveRoom(){
        currentGameRoomOperator.leaveRoom();
        currentGameRoomOperator = null;
    }

    private List<AddressDto> makeGameMap(Location location) throws IOException {
        // range map 생성
        MapRange mapRange = new GameMapRangeCalculator().getMapRange(location);

        // 서버로부터 맵 받아오기(10개)
        Call<String> call = RetrofitRwabClient.getMapAddressService().drawMapRangeRandom10(mapRange);
        String mapJson = call.execute().body();

        return new Gson().fromJson(mapJson, new TypeToken<ArrayList<AddressDto>>(){}.getType());
    }

    public void makeSingleRoom(Location location) {
        try {
            currentGameRoomOperator = new SingleGameRoomOperator(socketClient);
            currentGameRoomOperator.createMatch();
            // 맵 데이터 (게임 맵 생성 AddressDtoList를 생성자로 받아서 -> FlagGame으로 만든다.)
            List<AddressDto> addressDtoList = makeGameMap(location);
            // 액티비티를 시작한다. 이후의 처리는 액티비티 내부의 변수로 지정된 GameRoomOperator에게 넘긴다.

        } catch (ExecutionException | InterruptedException | IOException e) {
            e.printStackTrace();
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }

}
