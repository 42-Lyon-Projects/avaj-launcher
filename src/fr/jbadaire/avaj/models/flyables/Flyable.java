package fr.jbadaire.avaj.models.flyables;

import fr.jbadaire.avaj.exceptions.BadCoordinatesException;
import fr.jbadaire.avaj.exceptions.WeatherTowerNotFoundException;
import fr.jbadaire.avaj.models.towers.WeatherTower;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateConditions();
    public abstract String getSpecificWeatherMessage(final String weather);
    public abstract void move(final String weather) throws BadCoordinatesException, WeatherTowerNotFoundException;

    public void connectTower(WeatherTower tower) {
        this.weatherTower = tower;
        this.weatherTower.register(this);
    }

    public WeatherTower getWeatherTower() throws WeatherTowerNotFoundException {
        if (this.weatherTower == null) {
            throw new WeatherTowerNotFoundException("The weather tower is not registred !");
        }
        return weatherTower;
    }
}
