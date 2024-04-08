package fr.polytech.sim.log;

/**
 * Console logger.
 */
public class ConsoleLogger extends NamedLogger {

    /**
     * Constructor.
     *
     * @param name  logger name.
     */
    ConsoleLogger(String name) {
        super(name);
    }

    @Override
    public void operationLog(String message){
        System.out.print(message);
    }
}
