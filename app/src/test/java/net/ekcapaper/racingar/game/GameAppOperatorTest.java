package net.ekcapaper.racingar.game;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameAppOperatorTest {

    static GameAppOperator gameAppOperator;
    @BeforeClass
    public static void initGame(){
        gameAppOperator = new GameAppOperator();
    }

    @Test
    public void authEmail() {
        String email = "abcd@gmail.com";
        String password = "abcd1234";
        boolean result = gameAppOperator.authEmail(email,password);
        assertNotEquals(result,false);
    }

    @Test
    public void getClient() {
        assertNotNull(gameAppOperator.getClient());
    }

    @Test
    public void getSession() {
        String email = "abcd@gmail.com";
        String password = "abcd1234";
        boolean result = gameAppOperator.authEmail(email,password);
        assertNotEquals(result,false);

        assertNotNull(gameAppOperator.getSession());
    }
}