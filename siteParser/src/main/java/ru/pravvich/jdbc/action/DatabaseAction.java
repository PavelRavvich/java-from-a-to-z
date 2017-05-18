package ru.pravvich.jdbc.action;

import ru.pravvich.parser.PropertiesLoader;

import java.sql.Connection;

/**
 * Determines dependency injection for all action of database.
 */
class DatabaseAction {
    /**
     * Connection to database.
     */
    final Connection connection;
    /**
     * Connection to properties file.
     */
    final PropertiesLoader properties;

    /**
     * Default constructor for all child of DatabaseAction.
     * @param properties reader of file.
     * @param connection to database.
     */
    DatabaseAction(final PropertiesLoader properties,
                   final Connection connection) {

        this.connection = connection;
        this.properties = properties;
    }

}
