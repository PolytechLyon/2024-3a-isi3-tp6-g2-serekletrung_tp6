package fr.polytech.sim.log;

public class FileLogCreator implements LoggerCreator{

    FileLogCreator(){}
    public Logger create(String loggerName){
        return new FileLogger(loggerName);
    }
}
