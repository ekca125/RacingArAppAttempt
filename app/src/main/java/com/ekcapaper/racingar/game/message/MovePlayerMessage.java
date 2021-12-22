package com.ekcapaper.racingar.game.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MovePlayerMessage {
    String authToken;
    double latitude;
    double longitude;

    @Builder
    public MovePlayerMessage(String authToken, double latitude, double longitude) {
        this.authToken = authToken;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
