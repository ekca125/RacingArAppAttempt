package com.ekcapaper.racingar.game.operator.app;

import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.ekcapaper.racingar.game.board.FlagGameBoard;
import com.ekcapaper.racingar.game.operator.room.FlagGameRoomOperator;
import com.ekcapaper.racingar.game.operator.room.GameRoomOperator;
import com.ekcapaper.racingar.nakama.NakamaNetworkManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class GameAppOperator extends NakamaNetworkManager {
    private GameRoomOperator currentGameRoomOperator;
    private final ExecutorService executorService;

    public GameAppOperator() {
        super();
        executorService = Executors.newSingleThreadExecutor();
    }

    public boolean checkCurrentGameRoomOperator() {
        return currentGameRoomOperator != null;
    }

    public void leaveRoom() {
        currentGameRoomOperator.leaveRoom();
        currentGameRoomOperator = null;
    }

    public void makeSingleRoom(Location location, Consumer<Void> nextExecute) {
        Runnable makeRoomTask = () -> {
            try {
                FlagGameBoard flagGameBoard = new FlagGameBoard(1, location);
                flagGameBoard.drawFlags();
                if (flagGameBoard.isDrew()) {
                    FlagGameRoomOperator flagGameRoomOperator = new FlagGameRoomOperator(session, socketClient, flagGameBoard);
                    flagGameRoomOperator.createMatch();
                    currentGameRoomOperator = flagGameRoomOperator;
                } else {
                    currentGameRoomOperator = null;
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                currentGameRoomOperator = null;
            }
        };
        CompletableFuture.runAsync(makeRoomTask, executorService).thenAccept(nextExecute);
    }

    public GameRoomOperator getCurrentGameRoomOperator() {
        return currentGameRoomOperator;
    }
}
