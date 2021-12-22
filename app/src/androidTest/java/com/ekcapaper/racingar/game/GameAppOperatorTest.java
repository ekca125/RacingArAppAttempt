package com.ekcapaper.racingar.game;

import static org.junit.Assert.*;

import com.ekcapaper.racingar.game.operator.GameAppOperator;

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
}