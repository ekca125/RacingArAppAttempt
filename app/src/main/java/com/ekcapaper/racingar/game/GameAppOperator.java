package com.ekcapaper.racingar.game;

import com.ekcapaper.racingar.nakama.NakamaNetworkManager;

import java.util.concurrent.ExecutionException;

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

    public void makeSingleRoom(){
        try {
            currentGameRoomOperator = new SingleGameRoomOperator(socketClient);
            currentGameRoomOperator.createMatch();
            // 서버에서 맵을 생성하여 받아오기

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }

}
