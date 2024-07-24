package com.jotrorox.db;

/**
 * The database queries.
 * This enum contains all the queries that are used in the database.
 */
public enum DBQueries {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, user_id TEXT, name TEXT, profile_picture TEXT, servers TEXT)"),
    INSERT_USER("INSERT INTO users (user_id, name, profile_picture, servers) VALUES (?, ?, ?, ?)"),
    SELECT_USER("SELECT * FROM users WHERE user_id = ?"),
    UPDATE_USER("UPDATE users SET name = ?, profile_picture = ?, servers = ? WHERE user_id = ?"),
    DELETE_USER("DELETE FROM users WHERE user_id = ?");

    private final String query;

    /**
     * Creates a new database query.
     *
     * @param query The query.
     */
    DBQueries(String query) {
        this.query = query;
    }

    /**
     * Gets the query.
     *
     * @return The query.
     */
    public String getQuery() {
        return query;
    }
}
