package net.ekcapaper.racingar.game;

public class GameFlag extends GameLocation{
    GamePlayer owner;
    public GameFlag() {
        this.owner = null;
    }

    boolean isOwned(){
        return owner != null;
    }


}
