package com.ekcapaper.racingar.game.player;

import android.location.Location;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GamePlayer {
    String userIdentifier;
    String userId;
    String userName;

    @Setter
    Location location;

    @Builder
    public GamePlayer(String userId, String userName, Location location) {
        this.userId = userId;
        this.userName = userName;
        this.location = location;
        // 유저 구별자
        this.userIdentifier = userName;
    }
}
