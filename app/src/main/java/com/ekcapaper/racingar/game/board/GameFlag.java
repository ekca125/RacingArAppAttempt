package com.ekcapaper.racingar.game.board;

import com.ekcapaper.racingar.dto.AddressDto;

import lombok.Getter;
import lombok.Setter;

public class GameFlag extends AddressDto {
    @Setter
    @Getter
    String owner;

    public GameFlag(long id, double latitude, double longitude) {
        super(id, latitude, longitude);
        owner = null;
    }

    public GameFlag(AddressDto addressDto){
        super(addressDto.getId(),addressDto.getLatitude(),addressDto.getLongitude());
        owner = null;
    }

    public boolean checkOwned(){
        return owner != null;
    }
}
