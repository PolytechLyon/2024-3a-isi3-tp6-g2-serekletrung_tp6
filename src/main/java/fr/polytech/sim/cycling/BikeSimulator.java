package fr.polytech.sim.cycling;

import fr.polytech.sim.Simulation;
import fr.polytech.sim.log.FileLogger;
import fr.polytech.sim.log.Logger;
import fr.polytech.sim.log.LoggerCreator;
import fr.polytech.sim.log.TimestampedLoggerDecorator;
import fr.polytech.sim.utils.Context;

/**
 * Bike simulation.
 */
public class BikeSimulator implements Simulation {
    private final Logger logger = new TimestampedLoggerDecorator(LoggerCreator.getLoggerCreator().create("BikeSimulator"));

    public void run() {
        for (Bike bike : Context.injectAll(Bike.class)) {
            this.logger.log("Bike's speed %.2f Km/h.", bike.getVelocity());
            this.logger.log("Bike's mass %.2f Kg.", bike.getMass());
        }
    }
}
