package com.ekcapaper.racingar.game.board;

import android.location.Location;

import com.ekcapaper.racingar.game.message.MovePlayerMessage;

import java.util.stream.Collectors;

public class FlagSingleGameBoard extends FlagGameBoard{
    // 50 미터
    private final static double FLAG_GET_DISTANCE = 50;
    public FlagSingleGameBoard(double mapSize, Location location) {
        super(mapSize, location);
    }

    @Override
    public void movePlayer(MovePlayerMessage movePlayerMessage) {
        Location playerLocation = new Location("");
        playerLocation.setLatitude(movePlayerMessage.getLatitude());
        playerLocation.setLongitude(movePlayerMessage.getLongitude());

        this.currentPlayerLocation = playerLocation;
        for(int i=0; i<gameFlagList.size();i++){
            GameFlag gameFlag = gameFlagList.get(i);
            if(!gameFlag.checkOwned()){
                Location address1 = new Location("");
                address1.setLatitude(gameFlag.getLatitude());
                address1.setLongitude(gameFlag.getLongitude());

                Location address2 = currentPlayerLocation;

                double distance = address1.distanceTo(address2);
                if(distance < 0){
                    distance = -distance;
                }

                if(distance <= FLAG_GET_DISTANCE){
                    gameFlag.setOwner(movePlayerMessage.getUserIdentifier());
                }
            }
        }
    }
}
