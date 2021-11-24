package net.ekcapaper.racingar.game;

public class GameLocation {
    private final double latitude;
    private final double longitude;

    public GameLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
}
