package com.ekcapaper.racingar.game.message;

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
}
