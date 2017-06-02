package ru.pravvich.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database Connections.
 */
public class DatabaseConnection {
    /**
     * For authentication database.
     */
    private final PropertiesLoader properties;

    /**
     * Default constructor.
     *
     * @param properties authentication database.
     */
    public DatabaseConnection(final PropertiesLoader properties) {
        this.properties = properties;
    }

    /**
     * Create new Connection.
     *
     * @return new Connection to database.
     */
    public Connection getConnection() throws SQLException {

            return DriverManager.getConnection(
                    properties.get("url"),
                    properties.get("username"),
                    properties.get("password")
            );

    }
}
