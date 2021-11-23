package net.ekcapaper.racingar.activity;

import android.os.Bundle;

import net.ekcapaper.racingar.components.activity.login.LoginCardLight;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends LoginCardLight {
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.executorService = Executors.newSingleThreadExecutor();
    }
}
