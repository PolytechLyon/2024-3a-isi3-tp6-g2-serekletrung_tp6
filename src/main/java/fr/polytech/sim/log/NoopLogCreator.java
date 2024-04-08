package fr.polytech.sim.log;

public class NoopLogCreator implements LoggerCreator {

    NoopLogCreator() {}
    public Logger create(String loggerName) {
        return new NoopLogger();
    }
}
