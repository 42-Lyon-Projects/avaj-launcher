package fr.jbadaire.avaj.providers;

import fr.jbadaire.avaj.models.Coordinates;

import java.util.Random;

public class WeatherProvider {

    private static WeatherProvider instance;
    private final String[] weather;

    private WeatherProvider() {
        this.weather = new String[]{"FOG", "RAIN", "SNOW", "SUN"};
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        return this.weather[generateWeather(p_coordinates.getLongitude(), p_coordinates.getHeight(), p_coordinates.getLatitude())];
    }

    private int generateWeather(long x, long y, long z) {
        final long hash =  x * 66 + y * 17 + z * 4 + new Random().nextInt(16);
        final int choice = (int) Math.abs(hash);
        return choice % 4;
    }

    public static WeatherProvider getInstance() {
        return instance == null ? instance = new WeatherProvider() : instance;
    }
}
