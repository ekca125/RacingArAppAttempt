package com.ekcapaper.racingar.game.operator;

import android.location.Location;

import com.ekcapaper.racingar.game.board.FlagSingleGameBoard;
import com.ekcapaper.racingar.game.message.MessageOpCodeStorage;
import com.ekcapaper.racingar.game.message.MovePlayerMessage;
import com.google.gson.Gson;
import com.heroiclabs.nakama.AbstractSocketListener;
import com.heroiclabs.nakama.MatchData;
import com.heroiclabs.nakama.Session;
import com.heroiclabs.nakama.SocketClient;
import com.heroiclabs.nakama.SocketListener;

import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class SingleGameRoomOperator extends GameRoomOperator {
    private FlagSingleGameBoard flagSingleGameBoard;

    public SingleGameRoomOperator(Session session, SocketClient socketClient, FlagSingleGameBoard flagSingleGameBoard) {
        super(session, socketClient);
        this.flagSingleGameBoard = flagSingleGameBoard;
    }

    @Override
    protected void registerMovePlayerCallback(Consumer<Location> receiveMessage) {
        SocketListener listener = new AbstractSocketListener() {
            @Override
            public void onMatchData(final MatchData matchData) {
                switch ((int) matchData.getOpCode()){
                    case MessageOpCodeStorage.MOVE_PLAYER_MESSAGE:
                        Gson gson = new Gson();
                        String json = new String(matchData.getData(), StandardCharsets.UTF_8);
                        MovePlayerMessage movePlayerMessage = gson.fromJson(json,MovePlayerMessage.class);

                        Location location = new Location("");
                        location.setLatitude(movePlayerMessage.getLatitude());
                        location.setLongitude(movePlayerMessage.getLongitude());

                        flagSingleGameBoard.movePlayer(location);
                        //System.out.format("Received match data %s with opcode %d", matchData.getData(), matchData.getOpCode());
                        break;
                    default:
                        break;
                }
            }
        };
        socketClient.connect(session,listener);
    }
}
