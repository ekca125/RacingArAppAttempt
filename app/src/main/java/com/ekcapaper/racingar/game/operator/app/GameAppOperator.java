package com.ekcapaper.racingar.game.operator.app;

import android.location.Location;

import com.ekcapaper.racingar.game.board.FlagGameBoard;
import com.ekcapaper.racingar.game.operator.room.FlagGameRoomOperator;
import com.ekcapaper.racingar.game.operator.room.GameRoomOperator;
import com.ekcapaper.racingar.nakama.NakamaNetworkManager;

import java.util.concurrent.ExecutionException;

public class GameAppOperator extends NakamaNetworkManager {
    private GameRoomOperator currentGameRoomOperator;

    public GameAppOperator() {
        super();
    }

    public boolean checkCurrentGameRoomOperator(){
        return currentGameRoomOperator != null;
    }

    public void leaveRoom(){
        currentGameRoomOperator.leaveRoom();
        currentGameRoomOperator = null;
    }

    public void makeSingleRoom(Location location) {
        FlagGameBoard flagGameBoard = new FlagGameBoard(1,location);
        flagGameBoard.drawFlags();
        if(flagGameBoard.isDrew()){
            FlagGameRoomOperator flagGameRoomOperator = new FlagGameRoomOperator(session, socketClient, flagGameBoard);
            try {
                flagGameRoomOperator.createMatch();
                currentGameRoomOperator = flagGameRoomOperator;
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                currentGameRoomOperator = null;
            }
        }
        else{
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }
}
