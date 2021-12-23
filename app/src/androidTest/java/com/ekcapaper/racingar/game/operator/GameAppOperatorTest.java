package com.ekcapaper.racingar.game.operator;

import static org.junit.Assert.*;

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
    public void checkCurrentGameRoomOperator() {
    }

    @Test
    public void leaveRoom() {
    }

    @Test
    public void makeSingleRoom() {
    }

    @Test
    public void getCurrentGameRoomOperator() {
    }
}