package fr.polytech.sim.transport;

import fr.polytech.sim.Clock;
import fr.polytech.sim.cycling.Bike;
import fr.polytech.sim.log.ConsoleLogger;
import fr.polytech.sim.log.Logger;
import fr.polytech.sim.log.LoggerCreator;
import fr.polytech.sim.log.TimestampedLoggerDecorator;

import java.util.Objects;

/**
 * A wheel that can be used with different vehicle types.
 */
public class Wheel implements MobileObject {
    private static final double DEFAULT_MASSE = 10;

    private final Logger logger = new TimestampedLoggerDecorator(LoggerCreator.getLoggerCreator().create("Wheel"));
    private final Clock clock = Clock.getInstance();
    private final Vehicle vehicle;

    /**
     * Constructor.
     *
     * @param vehicle  the object providing push power.
     */
    public Wheel(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle must not be null.");
        this.vehicle = vehicle;
    }

    @Override
    public double getVelocity() {
        final double acceleration = this.vehicle.getPush() / this.getMass();
        final int time = this.clock.getTime();
        double velocity = time * acceleration;

        this.logger.log("Velocity %.2f Km/h at T %d s.", velocity, time);
        return velocity;
    }

    @Override
    public double getMass() {
        return DEFAULT_MASSE;
    }
}
