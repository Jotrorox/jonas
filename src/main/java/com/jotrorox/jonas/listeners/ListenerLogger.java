package com.jotrorox.jonas.listeners;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

/**
 * A centralized logger for all listeners.
 * This logger is used for the events that occur in the listeners.
 */
public class ListenerLogger {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ListenerLogger.class);

    /**
     * Gets the logger for the listeners.
     *
     * @return The logger for the listeners.
     */
    public static Logger getLogger() {
        return logger;
    }
}
