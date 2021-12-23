package com.ekcapaper.racingar.game.message;

import android.location.Location;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovePlayerMessage {
    String userIdentifier;
    double latitude;
    double longitude;

    @Builder
    public MovePlayerMessage(String userIdentifier, double latitude, double longitude) {
        this.userIdentifier = userIdentifier;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location getLocation(){
        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }
}
