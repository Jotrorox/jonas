package com.jotrorox.jonas.listeners;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * A centralized logger for all listeners.
 */
public class ListenerLogger {

    /**
     * The logger for all listeners.
     * 
     * A standard Logback logger.
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ListenerLogger.class);

    /**
     * Get the logger for all listeners.
     * 
     * @return The logger for all listeners.
     */
    public static Logger getLogger() {
        return logger;
    }
}
