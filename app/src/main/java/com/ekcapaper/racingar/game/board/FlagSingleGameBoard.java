package com.ekcapaper.racingar.game.board;

import android.location.Location;

import java.util.stream.Collectors;

public class FlagSingleGameBoard extends FlagGameBoard{
    // 50 미터
    private final static double FLAG_GET_DISTANCE = 50;
    public FlagSingleGameBoard(double mapSize, Location location) {
        super(mapSize, location);
    }

    @Override
    public void movePlayer(Location location) {
        this.currentPlayerLocation = location;
        addressDtoList = addressDtoList
                .stream()
                .filter((addressDto -> {
                    Location address1 = new Location("");
                    address1.setLatitude(addressDto.getLatitude());
                    address1.setLongitude(addressDto.getLongitude());

                    Location address2 = currentPlayerLocation;
                    return !(address1.distanceTo(address2) < FLAG_GET_DISTANCE);
                }))
                .collect(Collectors.toList());
    }
}
