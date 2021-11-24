package net.ekcapaper.racingar.game;

import android.location.Location;

public abstract class GameBoard {
    protected GamePlayer currentPlayer;
    public void moveCurrentPlayer(GameLocation gameLocation){
        currentPlayer.moveGamePlayer(gameLocation);
    }
}
