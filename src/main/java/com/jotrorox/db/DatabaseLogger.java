package com.jotrorox.db;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * A centralized logger for the database.
 * This logger is used for the events that occur in the database.
 */
public class DatabaseLogger {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(DatabaseLogger.class);

    /**
     * Gets the logger for the database.
     *
     * @return The logger for the database.
     */
    public static Logger getLogger() {
        return logger;
    }
}
