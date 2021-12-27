package com.ekcapaper.racingar.nakama;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class NakamaNetworkManagerTest {
    static NakamaNetworkManager nakamaNetworkManager;

    @BeforeClass
    public static void initManager() throws ExecutionException, InterruptedException {
        String email = "abcd@gmail.com";
        String password = "abcd1234";

        nakamaNetworkManager = new NakamaNetworkManager();
        nakamaNetworkManager.makeClient();
        nakamaNetworkManager.makeSession(email, password);
        nakamaNetworkManager.makeSocketClient(() -> {
        }, () -> {
        });
    }

    @Test
    public void checkClient() {
        assertTrue(nakamaNetworkManager.checkClient());
    }

    @Test
    public void checkSession() {
        assertTrue(nakamaNetworkManager.checkSession());
    }

    @Test
    public void checkSocketClient() {
        assertTrue(nakamaNetworkManager.checkSocketClient());
    }
}