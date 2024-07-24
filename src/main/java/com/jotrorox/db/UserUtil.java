package com.jotrorox.db;

import ch.qos.logback.classic.Logger;
import com.jotrorox.Jonas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserUtil {

    /**
     * Inserts a user into the database.
     *
     * @param id The ID of the user.
     * @param name The name of the user.
     * @param avatarUrl The avatar URL of the user.
     * @param guildId The ID of the guild the user is in.
     */
    public static void insertUser(String id, String name, String avatarUrl, String guildId) {
        Logger logger = DatabaseLogger.getLogger();
        Jonas.getDatabaseManager().connect();

        try (PreparedStatement preparedStatement = Jonas.getDatabaseManager().prepareStatement(DBQueries.INSERT_USER.getQuery())) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, avatarUrl);
            preparedStatement.setString(4, guildId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to execute the prepared statement!", e);
        }

        Jonas.getDatabaseManager().disconnect();
    }
}
