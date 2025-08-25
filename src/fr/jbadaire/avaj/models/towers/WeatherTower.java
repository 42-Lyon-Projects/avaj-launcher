package fr.jbadaire.avaj.models.towers;

import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.providers.WeatherProvider;

public class WeatherTower extends Tower{

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
    }

    public void changeWeather()
    {
        super.conditionChanged();
    }

}
