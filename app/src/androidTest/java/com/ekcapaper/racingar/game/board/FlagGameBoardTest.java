package com.ekcapaper.racingar.game.board;

import static org.junit.Assert.*;

import android.location.Location;
import android.util.Log;

import org.junit.Test;

public class FlagGameBoardTest {

    @Test
    public void drawFlags() {
        double latitude = 35.0979529784;
        double longitude = 129.0219886069;

        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        FlagGameBoard flagGameBoard = new FlagSingleGameBoard(1.0,location);
        flagGameBoard.drawFlags();
        Log.d("test", String.valueOf(flagGameBoard.getGameFlagList().size()));
        assertNotNull(flagGameBoard.getGameFlagList());
    }
}