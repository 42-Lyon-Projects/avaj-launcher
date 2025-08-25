package fr.jbadaire.avaj;


import fr.jbadaire.avaj.models.ScenarioModel;
import fr.jbadaire.avaj.models.towers.WeatherTower;
import fr.jbadaire.avaj.utils.ScenarioParser;
import fr.jbadaire.avaj.utils.SimulationLogger;

import java.io.FileNotFoundException;

public class AvajLauncher {

    public static void main(String[] args) {
        if (args.length == 0) {
            SimulationLogger.LOGGER.severe("Usage: java AvajLauncher.jar <scenarios>");
            System.exit(1);
        }
        try {
            SimulationLogger.LOGGER.info("<-- Loading Scenario... -->");
            final ScenarioModel model = ScenarioParser.loadScenarioContent(args[0]);
            SimulationLogger.LOGGER.info("<-- Scenario Loaded ! -->");
            SimulationLogger.LOGGER.info("<-- Connecting AirCrafts to Tower... -->");
            final WeatherTower weatherTower = new WeatherTower();
            model.getLoadedFlyables().forEach(flyable -> flyable.connectTower(weatherTower));
            SimulationLogger.LOGGER.info("<-- AirCrafts (" + weatherTower.getObservers().size() + ") Successfully Connected to Tower ! -->");

            SimulationLogger.LOGGER.info("<-- Start Simulation -->");
            for (int i = 0; i < model.getWeatherChanges(); i++) {
                if (weatherTower.getObservers().isEmpty()) {
                    SimulationLogger.LOGGER.info("<-- Simulation Ended -->");
                    SimulationLogger.LOGGER.info("Simulation stopped because all aircrafts are landed.");
                    SimulationLogger.LOGGER.info(i + " weather changes done on " + model.getWeatherChanges() + " expected.");
                    break;
                }
                SimulationLogger.LOGGER.info("<-- Change " + i + " -->");
                weatherTower.changeWeather();
            }
            if (!weatherTower.getObservers().isEmpty())
                SimulationLogger.LOGGER.info("<-- Simulation Ended -->");
            int airscraftLanded = model.getLoadedFlyables().size() - weatherTower.getObservers().size();
            SimulationLogger.LOGGER.info("On " + model.getLoadedFlyables().size() + " aircrafts loaded in the scenario " + airscraftLanded + " is landed.");
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.exit(1);
        }
    }

}
