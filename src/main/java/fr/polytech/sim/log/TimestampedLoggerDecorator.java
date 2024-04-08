package fr.polytech.sim.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampedLoggerDecorator extends LoggerDecorator {

    public TimestampedLoggerDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logger.log(timestamp + " - " + message);
    }
}