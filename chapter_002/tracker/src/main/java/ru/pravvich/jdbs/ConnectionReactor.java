package ru.pravvich.jdbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create, open and close connection to database.
 */
public class ConnectionReactor {
    /**
     * Connection to database reference.
     */
    private Connection connection;
    /**
     * Data about statements database.
     */
    private final PropertyLoader property;

    /**
     * Default constructor.
     * @param property data about statements database.
     */
    public ConnectionReactor(final PropertyLoader property) {

        this.property = property;

    }

    /**
     * Getter for this.connection.
     * @return connection.
     */
    public Connection get() throws SQLException {

        checkConnection();

        return connection;
    }

    /**
     * Init connection if connect is not active.
     * @throws SQLException if connection is fail.
     */
    private void checkConnection() throws SQLException {

        if (this.connection == null || this.connection.isClosed()) {


            this.connection = DriverManager.getConnection(

                    this.property.getUrlDB(),
                    this.property.getUsername(),
                    this.property.getPassword());

        }

    }

    /**
     * Close connection.
     * @throws SQLException if close is fail.
     */
    public void closeConnection() throws SQLException {

        if (this.connection != null) this.connection.close();

    }
}
