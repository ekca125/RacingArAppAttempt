package net.ekcapaper.racingar.game;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.List;
import java.util.stream.Collectors;

public class GameFlagBoard extends GameBoard {
    List<GameFlag> gameFlagList;
    double acquireDistance;

    public void setGameFlagList(List<GameFlag> gameFlagList) {
        this.gameFlagList = gameFlagList;
    }

    @Override
    public void moveCurrentPlayer(GameLocation gameLocation) {
        super.moveCurrentPlayer(gameLocation);
        gameFlagList = gameFlagList.stream().map(gameFlag -> {
            if(gameFlag.isOwned()){
                return gameFlag;
            }
            else{
                LatLng latLngFlag = new LatLng(gameFlag.getLatitude(),gameFlag.getLongitude());
                LatLng latLngPlayer = new LatLng(currentPlayer.getGameLocation().getLatitude(),currentPlayer.getGameLocation().getLongitude());
                double distance = SphericalUtil.computeDistanceBetween(latLngFlag, latLngPlayer);
                if(acquireDistance > distance){
                    gameFlag.setOwner(this.currentPlayer);
                    return gameFlag;
                }
            }

            return gameFlag;
        }).collect(Collectors.toList());
    }
}
