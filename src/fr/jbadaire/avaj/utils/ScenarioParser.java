package fr.jbadaire.avaj.utils;

import fr.jbadaire.avaj.factories.AircraftFactory;
import fr.jbadaire.avaj.models.Coordinates;
import fr.jbadaire.avaj.exceptions.BadCoordinatesException;
import fr.jbadaire.avaj.exceptions.InvalidScenarioException;
import fr.jbadaire.avaj.models.ScenarioModel;
import fr.jbadaire.avaj.models.flyables.Flyable;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScenarioParser {

    /**
     * Load the scenario form file.
     *
     * @param scenarioName the name of the file with scenario
     * @return loaded Scenario or null
     * @throws FileNotFoundException
     */
    public static ScenarioModel loadScenarioContent(String scenarioName) throws FileNotFoundException {
        final File scenarioFile = new File(scenarioName);
        if (!scenarioFile.exists() || !scenarioFile.isFile() || !scenarioFile.canRead())
            throw new FileNotFoundException("Unable to read the file " + scenarioName);
        try {
            return parseScenarioContent(Files.readAllLines(scenarioFile.toPath()));
        } catch (IOException exception) {
            SimulationLogger.LOGGER.severe("Unable to open the file " + scenarioName);
            System.exit(1);
        } catch (InvalidScenarioException exception) {
            SimulationLogger.LOGGER.severe(exception.getMessage());
            System.exit(1);
        }
        return null;
    }

    private static ScenarioModel parseScenarioContent(List<String> scenarioLines) throws InvalidScenarioException {
        scenarioLines = scenarioLines.stream().filter(s -> !s.isBlank()).map(String::trim).collect(Collectors.toList());
        if (scenarioLines.size() < 2) throw new InvalidScenarioException("Scenario has less than 2 lines");
        try {
            int nbOfWeatherChange = Integer.parseInt(scenarioLines.get(0));
            if (nbOfWeatherChange <= 0) throw new NumberFormatException();
            final List<Flyable> flyables = new ArrayList<>(scenarioLines.size() - 1);

            for (int i = 1; i < scenarioLines.size(); i++) {
                final String aircraftConfig = scenarioLines.get(i);
                final String[] aircraftConfigArgs = aircraftConfig.split(" ");
                if (aircraftConfigArgs.length != 5)
                    throw new InvalidScenarioException("The aircraft configuration does not include 5 arguments. (more or less)");
                if (!isAircraft(aircraftConfigArgs[0]))
                    throw new InvalidScenarioException("The args 1 of line " + i + " is invalid.");
                final Coordinates aircraftCoordinate = Coordinates.from(aircraftConfigArgs[2], aircraftConfigArgs[3], aircraftConfigArgs[4]);
                flyables.add(AircraftFactory.getInstance().newAircraft(aircraftConfigArgs[0], aircraftConfigArgs[1], aircraftCoordinate));
            }
            return new ScenarioModel(nbOfWeatherChange, flyables);
        } catch (NumberFormatException exception) {
            throw new InvalidScenarioException("Scenario has an invalid number of weather changes.");
        } catch (BadCoordinatesException exception) {
            throw new InvalidScenarioException("Scenario has invalid coordinates.");
        }
    }

    private static boolean isAircraft(final String type) {
        return type.equalsIgnoreCase("HELICOPTER") || type.equalsIgnoreCase("BALOON") || type.equalsIgnoreCase("JETPLANE");
    }


}
