package com.ekcapaper.racingar.game.board;

import android.location.Location;

import com.ekcapaper.racingar.game.player.GamePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiGameBoard extends GameBoard {
    private List<GamePlayer> gamePlayerList;

    public MultiGameBoard(double mapLengthKilometer, Location mapCenter) {
        super(mapLengthKilometer, mapCenter);
        gamePlayerList = new ArrayList<>();
    }

    public void addPlayer(GamePlayer gamePlayer) {
        gamePlayerList.add(gamePlayer);
    }

    public void removePlayer(String userIdentifier) {
        gamePlayerList = gamePlayerList
                .stream()
                .filter((gamePlayer -> !gamePlayer.getUserIdentifier().equals(userIdentifier)))
                .collect(Collectors.toList());
    }

    public void movePlayer(String userIdentifier, Location location) {
        for (int i = 0; i < gamePlayerList.size(); i++) {
            GamePlayer gamePlayer = gamePlayerList.get(i);
            if (gamePlayer.getUserIdentifier().equals(userIdentifier)) {
                gamePlayer.setLocation(location);
            }
        }
    }
}
