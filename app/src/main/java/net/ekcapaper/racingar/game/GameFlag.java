package net.ekcapaper.racingar.game;

public class GameFlag extends GameLocation{
    GamePlayer owner;

    public GameFlag(double latitude, double longitude) {
        super(latitude, longitude);
        this.owner = null;
    }

    boolean isOwned(){
        return owner != null;
    }

    public void setOwner(GamePlayer owner) {
        this.owner = owner;
    }
}
