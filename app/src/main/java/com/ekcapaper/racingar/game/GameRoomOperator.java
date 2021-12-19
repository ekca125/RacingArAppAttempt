package com.ekcapaper.racingar.game;

import com.heroiclabs.nakama.Match;
import com.heroiclabs.nakama.SocketClient;

import java.util.concurrent.ExecutionException;

public abstract class GameRoomOperator {
    protected SocketClient socketClient;
    protected Match match;

    public GameRoomOperator(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void createMatch() throws ExecutionException, InterruptedException {
        this.match = socketClient.createMatch().get();
    }

    public void joinMatch(String matchId) throws ExecutionException, InterruptedException {
        this.match = socketClient.joinMatch(matchId).get();
    }

    public String getMatchId(){
        return match.getMatchId();
    }

    public void leaveRoom(){
        socketClient.leaveMatch(match.getMatchId());
        socketClient = null;
        match = null;
    }

    //abstract public void movePlayer();
}