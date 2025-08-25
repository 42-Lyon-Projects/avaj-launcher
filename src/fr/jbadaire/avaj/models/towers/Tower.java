package fr.jbadaire.avaj.models.towers;

import fr.jbadaire.avaj.exceptions.WeatherTowerNotFoundException;
import fr.jbadaire.avaj.models.flyables.aircrafts.Aircraft;
import fr.jbadaire.avaj.models.flyables.Flyable;
import fr.jbadaire.avaj.utils.SimulationLogger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        if (flyable instanceof Aircraft aircraft) {
            SimulationLogger.LOGGER.info("Tower says: " + aircraft.getPrefix() + " registered to weather tower");
        }
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        if (flyable instanceof Aircraft aircraft) {
            SimulationLogger.LOGGER.info("Tower says: " + aircraft.getPrefix() + " unregistered from weather tower.");
        }
        observers.remove(flyable);
    }

    protected void conditionChanged() {
        for (Flyable observer : observers) {
            Aircraft aircraft = (Aircraft) observer;
            aircraft.updateConditions();
            if (aircraft.getCoordinates().getHeight() == 0) {
                SimulationLogger.LOGGER.info(aircraft.getPrefix() + " landing.");
                unregister(aircraft);
                return;
            }
        }
    }

    public List<Flyable> getObservers() {
        return observers;
    }
}
