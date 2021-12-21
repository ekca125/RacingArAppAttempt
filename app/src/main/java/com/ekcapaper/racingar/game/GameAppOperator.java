package com.ekcapaper.racingar.game;

import android.location.Location;

import com.ekcapaper.racingar.dto.AddressDto;
import com.ekcapaper.racingar.nakama.NakamaNetworkManager;
import com.ekcapaper.racingar.retrofit.RetrofitRwabClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public void makeSingleRoom(Location location) {
        try {
            currentGameRoomOperator = new SingleGameRoomOperator(socketClient);
            currentGameRoomOperator.createMatch();
            // 맵 데이터 (게임 맵 생성 AddressDtoList를 생성자로 받아서 -> FlagGame으로 만든다.)
            // GameBoard gameBoard = new FlagGameBoard();
            // GameRoomOperator은 GameBoard를 받아서 진행한다.
            // 액티비티를 시작한다. 이후의 처리는 액티비티 내부의 변수로 지정된 GameRoomOperator에게 넘긴다.

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }

}
