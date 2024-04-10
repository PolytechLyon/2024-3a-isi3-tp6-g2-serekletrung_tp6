package fr.polytech.sim.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampedLoggerDecorator extends LoggerDecorator {
    public TimestampedLoggerDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String format, Object... args) {
        // Add timestamp to the log
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Format to [dd/mm/yyy | hh:mm:ss] - message
        timestamp = "[" + timestamp.substring(0, 10) + " | " + timestamp.substring(11, 19) + "]";
        logger.log(timestamp + " - " + format, args);
    }
}