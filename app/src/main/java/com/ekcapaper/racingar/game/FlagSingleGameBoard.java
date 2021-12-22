package com.ekcapaper.racingar.game;

import android.location.Location;

public class FlagSingleGameBoard extends FlagGameBoard{

    public FlagSingleGameBoard(double mapSize, Location location) {
        super(mapSize, location);
    }

    @Override
    public void movePlayer(Location location) {
        this.currentPlayerLocation = location;
        // 일정 이상의 거리에 가까이 도달하면 처리
    }
}
