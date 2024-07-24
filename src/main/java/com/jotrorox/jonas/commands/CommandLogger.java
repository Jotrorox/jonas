package com.jotrorox.jonas.commands;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

/**
 * A centralized logger for all commands.
 * This logger is used for the events that occur in the commands.
 */
public class CommandLogger {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CommandLogger.class);

    /**
     * Gets the logger for the commands.
     *
     * @return The logger for the commands.
     */
    public static Logger getLogger() {
        return logger;
    }
}
