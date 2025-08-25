package fr.jbadaire.avaj.models.flyables.aircrafts;

import fr.jbadaire.avaj.exceptions.BadCoordinatesException;
import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.utils.SimulationLogger;

public class Helicopter extends Aircraft {

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public String getSpecificWeatherMessage(final String weather) {
        if (weather.equalsIgnoreCase("FOG")) return "Helicopter is losing visibility in the fog.";
        if (weather.equalsIgnoreCase("RAIN")) return "Helicopter is facing heavy rain.";
        if (weather.equalsIgnoreCase("SNOW")) return "Helicopter is slowed down by the snow.";
        return "Helicopter is cruising under the sun.";
    }

    @Override
    public void updateConditions() {
        super.updateConditions();
    }

    @Override
    public void move(final String weather) {
        try {
            if (weather.equalsIgnoreCase("SUN")) this.coordinates.add(10, 0, (byte) 2);
            else if (weather.equalsIgnoreCase("RAIN")) this.coordinates.add(5, 0, (byte) 0);
            else if (weather.equalsIgnoreCase("FOG")) this.coordinates.substract(1, 0, (byte) 0);
            else if (weather.equalsIgnoreCase("SNOW")) this.coordinates.substract(0, 0, (byte) 12);
        } catch (BadCoordinatesException e) {
            SimulationLogger.LOGGER.severe(e.getMessage());
            System.exit(1);
        }
    }
}
