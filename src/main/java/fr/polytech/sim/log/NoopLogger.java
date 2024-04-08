package fr.polytech.sim.log;

/**
 * No-op logger.
 */
public class NoopLogger implements Logger {

    NoopLogger() {
    }

    public void log(String format, Object... args) {
        // Do nothing
    }
}
