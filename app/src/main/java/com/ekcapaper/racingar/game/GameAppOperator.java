package com.ekcapaper.racingar.game;

import android.content.Intent;
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
            FlagSingleGameBoard flagSingleGameBoard = new FlagSingleGameBoard(1, location);
            flagSingleGameBoard.drawFlags();
            if(flagSingleGameBoard.isDrew()){
                SingleGameRoomOperator singleGameRoomOperator = new SingleGameRoomOperator(session, socketClient, flagSingleGameBoard);
                singleGameRoomOperator.createMatch();
                currentGameRoomOperator = singleGameRoomOperator;
            }
            else{
                throw new InterruptedException();
            }
            currentGameRoomOperator.createMatch();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }
}
