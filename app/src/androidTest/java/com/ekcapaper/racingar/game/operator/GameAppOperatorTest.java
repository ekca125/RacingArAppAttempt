package com.ekcapaper.racingar.game.operator;

import static org.junit.Assert.*;

import android.location.Location;

import com.ekcapaper.racingar.game.message.MovePlayerMessage;
import com.ekcapaper.racingar.game.operator.app.GameAppOperator;
import com.ekcapaper.racingar.game.operator.room.FlagGameRoomOperator;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class GameAppOperatorTest {
    static GameAppOperator gameAppOperator;

    @BeforeClass
    public static void initGame() throws ExecutionException, InterruptedException {
        String email = "abcd@gmail.com";
        String password = "abcd1234";

        gameAppOperator = new GameAppOperator();
        gameAppOperator.makeClient();
        gameAppOperator.makeSession(email, password);
        gameAppOperator.makeSocketClient(() -> {
        }, () -> {
        });
    }

    @Test
    public void checkClient() {
        assertTrue(gameAppOperator.checkClient());
    }

    @Test
    public void checkSession() {
        assertTrue(gameAppOperator.checkSession());
    }

    @Test
    public void checkSocketClient() {
        assertTrue(gameAppOperator.checkSocketClient());
    }

    @Test
    public void singleGame() throws ExecutionException, InterruptedException {
        double latitude = 35.0979529784;
        double longitude = 129.0219886069;

        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        gameAppOperator.makeSingleRoom(location);
        boolean check = gameAppOperator.checkCurrentGameRoomOperator();
        assertTrue(check);

        FlagGameRoomOperator flagGameRoomOperator = (FlagGameRoomOperator) gameAppOperator.getCurrentGameRoomOperator();

        flagGameRoomOperator.createMatch();
        boolean check2 = gameAppOperator.checkCurrentGameRoomOperator();
        assertTrue(check2);

        flagGameRoomOperator.setAfterPlayerMoveCallback(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                MovePlayerMessage movePlayerMessage = (MovePlayerMessage) o;
            }
        });
        flagGameRoomOperator.startReceiveMessageCallback();
        flagGameRoomOperator.sendPlayerMoveMessage(location);
    }

}