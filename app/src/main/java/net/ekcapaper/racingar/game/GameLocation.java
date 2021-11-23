package net.ekcapaper.racingar.game;

public class GameLocation {
    double latitude;
    double longitude;

    public GameLocation() {
        this.latitude = 37.555078;
        this.longitude = 126.970702;
    }

    public GameLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
