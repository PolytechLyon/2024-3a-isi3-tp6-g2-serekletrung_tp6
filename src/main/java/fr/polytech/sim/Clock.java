package fr.polytech.sim;

import java.util.Random;

/**
 * A clock used to synchronize simulations.
 */
public class Clock {

    private static final Clock instance = new Clock();
    private final int time = new Random().nextInt(25);

    private Clock() {
    }

    public static Clock getInstance() {
        return instance;
    }
    /**
     * Random integer between 0 and 24 inclusive.
     */
    public int getTime() {
        return this.time;
    }
}
