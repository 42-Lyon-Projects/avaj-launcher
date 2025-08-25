package fr.jbadaire.avaj.models;

import fr.jbadaire.avaj.models.flyables.Flyable;

import java.util.Collections;
import java.util.List;

public class ScenarioModel {

    private final int weatherChanges;
    private final List<Flyable> loadedFlyables;

    public ScenarioModel(int weatherChanges, List<Flyable> loadedFlyables) {
        this.weatherChanges = weatherChanges;
        this.loadedFlyables = Collections.unmodifiableList(loadedFlyables);
    }

    public int getWeatherChanges() {
        return weatherChanges;
    }

    public List<Flyable> getLoadedFlyables() {
        return loadedFlyables;
    }
}
