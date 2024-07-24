package com.jotrorox.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A manager for the database.
 * This class is used to connect to the database, execute queries, and disconnect from the database.
 */
public class DatabaseManager {
    private final String DATABASE_URL;
    private Connection connection;

    /**
     * Creates a new database manager.
     *
     * @param url The URL of the database.
     */
    public DatabaseManager(String url) {
        DATABASE_URL = url;
    }

    /**
     * Connects to the database.
     */
    public void connect() {
        if (isConnected()) return;

        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            DatabaseLogger.getLogger().error("Failed to connect to the database!", e);
        }
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect() {
        if (!isConnected()) return;

        try {
            connection.close();
        } catch (SQLException e) {
            DatabaseLogger.getLogger().error("Failed to disconnect from the database!", e);
        }
    }

    /**
     * Executes a query on the database.
     *
     * @param query The query to execute.
     * @return The result of the query.
     */
    public boolean executeUpdate(String query) {
        if (!isConnected()) return false;

        try {
            connection.createStatement().executeUpdate(query);
            return true;
        } catch (SQLException e) {
            DatabaseLogger.getLogger().error("Failed to execute an update query!", e);
            return false;
        }
    }

    /**
     * Checks if the database is connected.
     *
     * @return True if the database is connected, false otherwise.
     */
    private boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            DatabaseLogger.getLogger().error("Failed to check if the database is connected!", e);
            return false;
        }
    }

    /**
     * Gets the URL of the database.
     *
     * @return The URL of the database.
     */
    public String getDatabaseUrl() {
        return DATABASE_URL;
    }

    /**
     * Gets the connection to the database.
     *
     * @return The connection to the database.
     */
    public Connection getConnection() {
        return connection;
    }
}
