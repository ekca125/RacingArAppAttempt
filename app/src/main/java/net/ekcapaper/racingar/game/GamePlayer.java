package net.ekcapaper.racingar.game;

public class GamePlayer {
    private GameLocation gameLocation;

    public GamePlayer(GameLocation gameLocation) {
        this.gameLocation = gameLocation;
    }

    public void moveGamePlayer(GameLocation gameLocation){
        this.gameLocation = gameLocation;
    }

    public GameLocation getGameLocation() {
        return gameLocation;
    }
}
