package fr.jbadaire.avaj.factories;

import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.models.flyables.Flyable;
import fr.jbadaire.avaj.models.flyables.aircrafts.Baloon;
import fr.jbadaire.avaj.models.flyables.aircrafts.Helicopter;
import fr.jbadaire.avaj.models.flyables.aircrafts.JetPlane;

public class AircraftFactory {

    private static AircraftFactory instance;
    private int lastId;

    private AircraftFactory() {
        lastId = 0;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        if (p_type == null || p_name == null || p_coordinates == null)
            throw new IllegalArgumentException("Aircraft type and/or name can't be null.");
        lastId++;
        if (p_type.equalsIgnoreCase("HELICOPTER")) return new Helicopter(lastId, p_name, p_coordinates);
        else if (p_type.equalsIgnoreCase("BALOON")) return new Baloon(lastId, p_name, p_coordinates);
        else if (p_type.equalsIgnoreCase("JETPLANE")) return new JetPlane(lastId, p_name, p_coordinates);
        throw new IllegalArgumentException("Invalid type");
    }

    public static AircraftFactory getInstance() {
        return instance == null ? instance = new AircraftFactory() : instance;
    }

}
