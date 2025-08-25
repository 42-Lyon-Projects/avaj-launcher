package fr.jbadaire.avaj.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class SimulationLogger {

    public static final Logger LOGGER = Logger.getLogger("SimulationLogger");


    static {
        try {
            final File logFile = new File("simulation.txt");
            if (logFile.exists()) logFile.delete();
            else logFile.createNewFile();

            LOGGER.setUseParentHandlers(false);
            FileHandler fh = new FileHandler("simulation.txt");
            LOGGER.addHandler(fh);
            Formatter formatter = new SimpleFormatter() {
                private String format = "[%1$tF %1$tT.%1$tL] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format, new Date(lr.getMillis()), lr.getLevel().getLocalizedName(),
                            lr.getMessage());
                }
            };
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            System.err.println("Error opening log file");
            System.exit(1);
        }
    }
}
