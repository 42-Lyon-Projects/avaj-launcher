package fr.jbadaire.avaj.models.flyables.aircrafts;

import fr.jbadaire.avaj.exceptions.BadCoordinatesException;
import fr.jbadaire.avaj.exceptions.WeatherTowerNotFoundException;
import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.models.flyables.Flyable;
import fr.jbadaire.avaj.utils.SimulationLogger;

public class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getPrefix() {
        return super.getClass().getSimpleName() + "#" + getName() + '(' + getId() + ')';
    }

    @Override
    public void updateConditions() {
        try {
            final String weather = getWeatherTower().getWeather(this.getCoordinates());
            final String specificWeatherMessage = getSpecificWeatherMessage(weather);
            SimulationLogger.LOGGER.info(getPrefix() + " : " + specificWeatherMessage);
            move(weather);
        } catch (WeatherTowerNotFoundException | BadCoordinatesException e) {
            System.exit(1);
        }
    }

    @Override
    public String getSpecificWeatherMessage(final String weather) {
        throw new IllegalAccessError("The function getSpecificWeatherMessage() is not implemented for this parent class.");
    }

    @Override
    public void move(final String weather) throws BadCoordinatesException, UnsupportedOperationException {
        throw new IllegalAccessError("The function move() is not implemented for this parent class.");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
