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
}
