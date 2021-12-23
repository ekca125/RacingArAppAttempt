package com.ekcapaper.racingar.game.operator;

import android.location.Location;

import com.ekcapaper.racingar.game.message.MessageOpCodeStorage;
import com.ekcapaper.racingar.game.message.MovePlayerMessage;
import com.google.gson.Gson;
import com.heroiclabs.nakama.AbstractSocketListener;
import com.heroiclabs.nakama.Match;
import com.heroiclabs.nakama.MatchData;
import com.heroiclabs.nakama.Session;
import com.heroiclabs.nakama.SocketClient;
import com.heroiclabs.nakama.SocketListener;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import lombok.Getter;

public abstract class GameRoomOperator {
    @Getter
    protected Session session;
    protected SocketClient socketClient;
    protected Match match;

    public GameRoomOperator(Session session, SocketClient socketClient) {
        this.session = session;
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

    public void sendPlayerMoveMessage(Location location){
        String username = session.getUsername();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        MovePlayerMessage movePlayerMessage = MovePlayerMessage.builder()
                .userIdentifier(username)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        Gson gson = new Gson();
        String payload = gson.toJson(movePlayerMessage);

        socketClient.sendMatchData(match.getMatchId(),MessageOpCodeStorage.MOVE_PLAYER_MESSAGE,payload.getBytes(StandardCharsets.UTF_8));
    }
    abstract public void startReceiveMessageCallback();
}