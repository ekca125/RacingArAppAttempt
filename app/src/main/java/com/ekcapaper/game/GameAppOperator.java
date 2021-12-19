package com.ekcapaper.game;

import com.heroiclabs.nakama.Client;
import com.heroiclabs.nakama.DefaultClient;
import com.heroiclabs.nakama.Session;

import net.ekcapaper.racingar.keystorage.KeyStorageNakama;
import net.ekcapaper.racingar.nakama.NakamaNetworkManager;

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

    public void makeSingleRoom() throws ExecutionException, InterruptedException {
        currentGameRoomOperator = new SingleGameRoomOperator(socketClient);
        currentGameRoomOperator.createMatch();
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }
}
