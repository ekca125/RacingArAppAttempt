package com.ekcapaper.racingar.game;

import com.heroiclabs.nakama.SocketClient;

public class SingleGameRoomOperator extends GameRoomOperator{
    private FlagGameBoard flagGameBoard;

    public SingleGameRoomOperator(SocketClient socketClient, FlagGameBoard flagGameBoard) {
        super(socketClient);
        this.flagGameBoard = flagGameBoard;
    }
}
