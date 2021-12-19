package com.ekcapaper.game;

import com.heroiclabs.nakama.Match;
import com.heroiclabs.nakama.SocketClient;

public class SingleGameRoomOperator extends GameRoomOperator{
    public SingleGameRoomOperator(SocketClient socketClient) {
        super(socketClient);
    }
}
