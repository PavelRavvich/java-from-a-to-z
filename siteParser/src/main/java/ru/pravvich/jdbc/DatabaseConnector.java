package ru.pravvich.jdbc;

import ru.pravvich.parser.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Support connection to database.
 */
public class DatabaseConnector {

    /**
     * For get keys to connection to DB.
     */
    private PropertiesLoader properties;
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Default constructor.
     *
     * @param properties ror get keys to connection to DB.
     */
    public DatabaseConnector(final PropertiesLoader properties) {
        this.properties = properties;
    }

    /**
     * Get Connection obj. If connection not active then activate.
     *
     * @return Connection to database.
     */
    public Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(
                        properties.getValue("url_database"),
                        properties.getValue("login_parser"),
                        properties.getValue("password_parser")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * Close connection.
     */
    void closeDatabaseConnection() {
        try {

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check active Connection.
     *
     * @return true if connection not active, else false.
     */
    boolean isClose() {
        try {

            return connection.isClosed();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
