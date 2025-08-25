package fr.jbadaire.avaj.models.flyables.aircrafts;

import fr.jbadaire.avaj.exceptions.BadCoordinatesException;
import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.utils.SimulationLogger;

public class JetPlane extends Aircraft {

    public JetPlane(long p_id, String id_name, Coordinates p_coordinates) {
        super(p_id, id_name, p_coordinates);
    }

    @Override
    public String getSpecificWeatherMessage(final String weather) {
        if (weather.equalsIgnoreCase("FOG")) return "JetPlane is flying cautiously in the fog.";
        if (weather.equalsIgnoreCase("RAIN")) return "JetPlane is cutting through the rain.";
        if (weather.equalsIgnoreCase("SNOW")) return "JetPlane is facing turbulence in the snow.";
        return "JetPlane is speeding through clear skies.";
    }

    @Override
    public void updateConditions() {
        super.updateConditions();
    }

    @Override
    public void move(final String weather) {
        try {
            if (weather.equalsIgnoreCase("SUN")) this.coordinates.add(0, 10, (byte) 2);
            else if (weather.equalsIgnoreCase("RAIN")) this.coordinates.add(0, 5, (byte) 0);
            else if (weather.equalsIgnoreCase("FOG")) this.coordinates.substract(0, 1, (byte) 0);
            else if (weather.equalsIgnoreCase("SNOW")) this.coordinates.substract(0, 0, (byte) 7);
        } catch (BadCoordinatesException e) {
            SimulationLogger.LOGGER.severe(e.getMessage());
            System.exit(1);
        }
    }
}
