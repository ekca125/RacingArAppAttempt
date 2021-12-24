package com.ekcapaper.racingar.game.operator.room;

import android.location.Location;

import com.ekcapaper.racingar.game.board.FlagGameBoard;
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

import lombok.Setter;

public class FlagGameRoomOperator extends GameRoomOperator {
    private FlagGameBoard flagGameBoard;

    public FlagGameRoomOperator(Session session, SocketClient socketClient, FlagGameBoard flagGameBoard) {
        super(session, socketClient);
        this.flagGameBoard = flagGameBoard;
    }

    @Setter
    private Consumer<Object> afterPlayerMoveCallback;

    // 오퍼레이터에서 처리해야할 내용
    public void startReceiveMessageCallback() {
        if (afterPlayerMoveCallback == null) {
            throw new NullPointerException();
        }

        SocketListener listener = new AbstractSocketListener() {
            @Override
            public void onMatchData(final MatchData matchData) {
                switch ((int) matchData.getOpCode()) {
                    case MessageOpCodeStorage.MOVE_PLAYER_MESSAGE:
                        Gson gson = new Gson();
                        String json = new String(matchData.getData(), StandardCharsets.UTF_8);
                        MovePlayerMessage movePlayerMessage = gson.fromJson(json, MovePlayerMessage.class);

                        flagGameBoard.movePlayer(movePlayerMessage.getUserIdentifier(), movePlayerMessage.getLocation());
                        afterPlayerMoveCallback.accept(movePlayerMessage);
                        break;
                    default:
                        break;
                }
            }
        };
        socketClient.connect(session, listener);
    }

}
