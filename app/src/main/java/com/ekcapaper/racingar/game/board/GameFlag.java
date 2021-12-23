package com.ekcapaper.racingar.game.board;

import com.ekcapaper.racingar.dto.AddressDto;

public class GameFlag extends AddressDto {
    public GameFlag(long id, double latitude, double longitude) {
        super(id, latitude, longitude);
    }

    public GameFlag(AddressDto addressDto){
        super(addressDto.getId(),addressDto.getLatitude(),addressDto.getLongitude());
    }
}
