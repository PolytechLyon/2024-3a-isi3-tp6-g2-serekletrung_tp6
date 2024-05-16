package fr.polytech.sim.log;

public interface LoggerCreator {

    LoggerCreator INSTANCE = new FileLogCreator();

    Logger create(String loggerName);

    static LoggerCreator getLoggerCreator(){
        return INSTANCE;
    }
}
