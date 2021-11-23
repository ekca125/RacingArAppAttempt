package net.ekcapaper.racingar.game;

public class GamePlayer {
    GameLocation gameLocation;

    public GamePlayer(GameLocation gameLocation) {
        this.gameLocation = gameLocation;
    }

    public GamePlayer() {
        this.gameLocation = new GameLocation();
    }

    public void moveGamePlayer(GameLocation gameLocation){
        this.gameLocation = gameLocation;
    }
}
