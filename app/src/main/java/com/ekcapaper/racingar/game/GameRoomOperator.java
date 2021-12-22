package com.ekcapaper.racingar.game;

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

public abstract class GameRoomOperator {
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
        registMovePlayerCallback();
    }

    public String getMatchId(){
        return match.getMatchId();
    }

    public void leaveRoom(){
        socketClient.leaveMatch(match.getMatchId());
        socketClient = null;
        match = null;
    }

    public void sendMovePlayerMessage(Location location){
        String authToken = session.getAuthToken();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        MovePlayerMessage movePlayerMessage = MovePlayerMessage.builder()
                .authToken(authToken)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        Gson gson = new Gson();
        String payload = gson.toJson(movePlayerMessage);

        socketClient.sendMatchData(match.getMatchId(),MessageOpCodeStorage.MOVE_PLAYER_MESSAGE,payload.getBytes(StandardCharsets.UTF_8));
    }


    public void registMovePlayerCallback(){
        SocketListener listener = new AbstractSocketListener() {
            @Override
            public void onMatchData(final MatchData matchData) {
                switch ((int) matchData.getOpCode()){
                    case MessageOpCodeStorage.MOVE_PLAYER_MESSAGE:
                        System.out.format("Received match data %s with opcode %d", matchData.getData(), matchData.getOpCode());
                        break;
                    default:
                        break;
                }

            }
        };
        socketClient.connect(session,listener);
    }
}