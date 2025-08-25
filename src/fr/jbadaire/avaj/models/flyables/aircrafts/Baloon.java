package fr.jbadaire.avaj.models.flyables.aircrafts;

import fr.jbadaire.avaj.exceptions.BadCoordinatesException;
import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.utils.SimulationLogger;

public class Baloon extends Aircraft {

    public Baloon(long p_id, String id_name, Coordinates p_coordinates) {
        super(p_id, id_name, p_coordinates);
    }

    @Override
    public String getSpecificWeatherMessage(final String weather) {
        if (weather.equalsIgnoreCase("FOG")) return "Baloon is flying blindly in the fog.";
        if (weather.equalsIgnoreCase("RAIN")) return "Baloon is getting soaked by the rain.";
        if (weather.equalsIgnoreCase("SNOW")) return "Baloon is struggling in the snow.";
        return "Baloon is enjoying the sunny weather.";
    }

    @Override
    public void updateConditions() {
        super.updateConditions();
    }

    @Override
    public void move(final String weather) {
        try {
            if (weather.equalsIgnoreCase("SUN")) this.coordinates.add(2, 0, (byte) 4);
            else if (weather.equalsIgnoreCase("RAIN"))
                this.coordinates.substract(0, 0, (byte) 5);
            else if (weather.equalsIgnoreCase("FOG"))
                this.coordinates.substract(0, 0, (byte) 3);
            else if (weather.equalsIgnoreCase("SNOW"))
                this.coordinates.substract(0, 0, (byte) 15);
        } catch (BadCoordinatesException e) {
            SimulationLogger.LOGGER.severe(e.getMessage());
            System.exit(1);
        }
    }
}
