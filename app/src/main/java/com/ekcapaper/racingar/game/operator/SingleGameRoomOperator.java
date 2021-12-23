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
    private Consumer<Object> afterPlayerMoveCallback;


    public SingleGameRoomOperator(Session session, SocketClient socketClient, FlagSingleGameBoard flagSingleGameBoard) {
        super(session, socketClient);
        this.flagSingleGameBoard = flagSingleGameBoard;

        this.afterPlayerMoveCallback = null;
    }

    public void startReceiveMessageCallback(){
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
                        afterPlayerMoveCallback.accept(new Object());
                        break;
                    default:
                        break;
                }
            }
        };
        socketClient.connect(session,listener);
    }

    // 설정되지 않은 상태에서 실행하면 null 오류
    public void setAfterPlayerMoveCallback(Consumer<Object> afterPlayerMoveCallback){
        this.afterPlayerMoveCallback = afterPlayerMoveCallback;
    }
}
