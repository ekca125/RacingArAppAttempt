package com.ekcapaper.racingar.game.message;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovePlayerMessage {
    String userName;
    double latitude;
    double longitude;

    @Builder
    public MovePlayerMessage(String userName, double latitude, double longitude) {
        this.userName = userName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
