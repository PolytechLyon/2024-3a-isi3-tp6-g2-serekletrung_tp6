package fr.polytech.sim.log;

public class ConsoleLogCreator implements LoggerCreator{

    ConsoleLogCreator(){}

    public Logger create(String loggerName){
        return new ConsoleLogger(loggerName);
    }
}
