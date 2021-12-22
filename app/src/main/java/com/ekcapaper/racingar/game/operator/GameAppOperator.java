package com.ekcapaper.racingar.game.operator;

import android.location.Location;

import com.ekcapaper.racingar.game.board.FlagSingleGameBoard;
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
        try {
            FlagSingleGameBoard flagSingleGameBoard = new FlagSingleGameBoard(1, location);
            flagSingleGameBoard.drawFlags();
            if(flagSingleGameBoard.isDrew()){
                SingleGameRoomOperator singleGameRoomOperator = new SingleGameRoomOperator(session, socketClient, flagSingleGameBoard);
                singleGameRoomOperator.createMatch();
                currentGameRoomOperator = singleGameRoomOperator;
            }
            else{
                throw new InterruptedException();
            }
            currentGameRoomOperator.createMatch();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }
}
