package com.ekcapaper.racingar.game.operator.room;

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

public class FlagGameRoomOperator extends GameRoomOperator {
    private FlagSingleGameBoard flagSingleGameBoard;
    private Consumer<Object> afterPlayerMoveCallback;

    public FlagGameRoomOperator(Session session, SocketClient socketClient, FlagSingleGameBoard flagSingleGameBoard) {
        super(session, socketClient);
        this.flagSingleGameBoard = flagSingleGameBoard;

        this.afterPlayerMoveCallback = null;

    }
    
    
    // 오퍼레이터에서 처리해야할 내용
    public void startReceiveMessageCallback(){
        if(afterPlayerMoveCallback == null){
            throw new NullPointerException();
        }

        SocketListener listener = new AbstractSocketListener() {
            @Override
            public void onMatchData(final MatchData matchData) {
                switch ((int) matchData.getOpCode()){
                    case MessageOpCodeStorage.MOVE_PLAYER_MESSAGE:
                        Gson gson = new Gson();
                        String json = new String(matchData.getData(), StandardCharsets.UTF_8);
                        MovePlayerMessage movePlayerMessage = gson.fromJson(json,MovePlayerMessage.class);

                        flagSingleGameBoard.movePlayer(movePlayerMessage);
                        afterPlayerMoveCallback.accept(movePlayerMessage);
                        break;
                    default:
                        break;
                }
            }
        };
        socketClient.connect(session,listener);
    }

    // 액티비티에서 추가적으로 처리해야 할 내용
    public void setAfterPlayerMoveCallback(Consumer<Object> afterPlayerMoveCallback){
        this.afterPlayerMoveCallback = afterPlayerMoveCallback;
    }

    public FlagSingleGameBoard getFlagSingleGameBoard() {
        return flagSingleGameBoard;
    }
}
