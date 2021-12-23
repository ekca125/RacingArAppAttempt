package com.ekcapaper.racingar.game.board;

import static org.junit.Assert.*;

import android.location.Location;
import android.util.Log;

import com.ekcapaper.racingar.game.board.GameBoard;
import com.ekcapaper.racingar.maptool.MapRange;

import org.junit.Test;

public class GameBoardTest {

    @Test
    public void test() {
        double latitude = 35.0979529784;
        double longitude = 129.0219886069;

        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        GameBoard gameBoard = new GameBoard(1.0, location);
        Log.d("abcd", gameBoard.getMapRange().toString());
        MapRange mapRange = gameBoard.getMapRange();
        assertTrue(mapRange.getStartLatitude()<mapRange.getEndLatitude());
        assertTrue(mapRange.getStartLongitude()<mapRange.getEndLongitude());
    }

}