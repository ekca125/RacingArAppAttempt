package com.ekcapaper.racingar.kit.data;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.ekcapaper.racingar.game.operator.app.GameAppOperator;

public class ThisApplication extends Application {
    GameAppOperator gameAppOperator;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.gameAppOperator = new GameAppOperator();
        this.gameAppOperator.makeClient();
    }

    public GameAppOperator getGameAppOperator() {
        return gameAppOperator;
    }
}
