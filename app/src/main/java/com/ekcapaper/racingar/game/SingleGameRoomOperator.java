package com.ekcapaper.racingar.game;

import com.ekcapaper.racingar.game.message.MessageOpCodeStorage;
import com.heroiclabs.nakama.AbstractSocketListener;
import com.heroiclabs.nakama.MatchData;
import com.heroiclabs.nakama.Session;
import com.heroiclabs.nakama.SocketClient;
import com.heroiclabs.nakama.SocketListener;

public class SingleGameRoomOperator extends GameRoomOperator {
    private FlagGameBoard flagGameBoard;

    public SingleGameRoomOperator(Session session, SocketClient socketClient, FlagGameBoard flagGameBoard) {
        super(session, socketClient);
        this.flagGameBoard = flagGameBoard;
    }


    @Override
    protected void registerMovePlayerCallback() {
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
