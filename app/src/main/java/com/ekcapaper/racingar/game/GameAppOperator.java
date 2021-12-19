package com.ekcapaper.racingar.game;

import android.location.Location;

import com.ekcapaper.racingar.maptool.MapRange;
import com.ekcapaper.racingar.maptool.MeterToLatitudeConverter;
import com.ekcapaper.racingar.maptool.MeterToLongitudeConverter;
import com.ekcapaper.racingar.nakama.NakamaNetworkManager;
import com.ekcapaper.racingar.retrofit.MapAddressService;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Retrofit;

public class GameAppOperator extends NakamaNetworkManager {
    private GameRoomOperator currentGameRoomOperator;

    // 맵의 크기 : 플레이어의 위치를 중앙으로 하고 가로 세로가 1km인 정사각형
    // 맵의 시작위치는 왼쪽아래이며 맵의 끝위치는 오른쪽위이다.
    final private double defaultMapSize = 1;

    public GameAppOperator() {
        super();
    }

    public boolean checkCurrentGameRoomOperator(){
        return currentGameRoomOperator != null;
    }

    public void leaveRoom(){
        currentGameRoomOperator.leaveRoom();
        currentGameRoomOperator = null;
    }

    private MapRange getMapRange(Location location){
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();

        MeterToLatitudeConverter meterToLatitudeConverter = new MeterToLatitudeConverter();
        MeterToLongitudeConverter meterToLongitudeConverter = new MeterToLongitudeConverter(currentLatitude);

        double distanceMeter = defaultMapSize/2;

        double startLatitude = currentLatitude - meterToLatitudeConverter.convertMeterToLatitude(distanceMeter);
        double startLongitude = currentLongitude - meterToLongitudeConverter.convertMeterToLongitude(distanceMeter);
        double endLatitude = currentLatitude + meterToLatitudeConverter.convertMeterToLatitude(distanceMeter);
        double endLongitude =  currentLongitude + meterToLongitudeConverter.convertMeterToLongitude(distanceMeter);

        return new MapRange(startLatitude,startLongitude,endLatitude,endLongitude);
    }


    public void makeSingleRoom(Location location){
        try {
            currentGameRoomOperator = new SingleGameRoomOperator(socketClient);
            currentGameRoomOperator.createMatch();
            // range map 생성
            MapRange mapRange = getMapRange(location);
            // 서버에서 맵을 생성하여 받아오기(정사각형 모양으로 1km내의 주소에서 랜덤으로 추출한다.)
            // Json으로 값을 전달하여 받아온다.
            String jsonMapRange = new Gson().toJson(mapRange);
            // Http로 값 받아오기
            // 테스트 필요
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://rawb.ekcapaper.net")
                    .build();
            MapAddressService mapAddressService = retrofit.create(MapAddressService.class);
            Call<String> mapAddressJsonString = mapAddressService.getMapAddress(jsonMapRange);

            // 액티비티를 시작한다. 이후의 처리는 액티비티 내부의 변수로 지정된 GameRoomOperator에게 넘긴다.

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            currentGameRoomOperator = null;
        }
    }

    public GameRoomOperator getCurrentGameRoomOperator(){
        return currentGameRoomOperator;
    }

}
