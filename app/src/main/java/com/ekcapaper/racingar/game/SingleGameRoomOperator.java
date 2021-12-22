package com.ekcapaper.racingar.game;

import com.heroiclabs.nakama.Session;
import com.heroiclabs.nakama.SocketClient;

public class SingleGameRoomOperator extends GameRoomOperator {
    private FlagGameBoard flagGameBoard;

    public SingleGameRoomOperator(Session session, SocketClient socketClient, FlagGameBoard flagGameBoard) {
        super(session, socketClient);
        this.flagGameBoard = flagGameBoard;
    }



}
