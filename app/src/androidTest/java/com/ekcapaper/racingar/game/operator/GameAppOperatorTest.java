package com.ekcapaper.racingar.game.operator;

import static org.junit.Assert.*;

import android.location.Location;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class GameAppOperatorTest {
    static GameAppOperator gameAppOperator;
    @BeforeClass
    public static void initGame() throws ExecutionException, InterruptedException {
        String email = "abcd@gmail.com";
        String password = "abcd1234";

        gameAppOperator = new GameAppOperator();
        gameAppOperator.makeClient();
        gameAppOperator.makeSession(email,password);
        gameAppOperator.makeSocketClient(()->{},()->{});
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
    public void singleGame() {
        double latitude = 35.0979529784;
        double longitude = 129.0219886069;

        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        gameAppOperator.makeSingleRoom(location);
        gameAppOperator.checkCurrentGameRoomOperator();
    }

}