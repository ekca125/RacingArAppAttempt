package net.ekcapaper.racingar.components.data;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import net.ekcapaper.racingar.game.GameAppOperator;

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
        gameAppOperator = new GameAppOperator();
    }

    public GameAppOperator getGameAppOperator() {
        return gameAppOperator;
    }
}
