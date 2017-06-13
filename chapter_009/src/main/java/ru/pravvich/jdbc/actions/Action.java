package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;

import java.sql.Connection;

/**
 * Determines default constructor for all actions classes which work with database.
 */
public class Action {
    /**
     * Connection to DB.
     */
    protected final Connection connection;
    /**
     * Load script for work with database from properties file.
     */
    final PropertiesLoader scripts;

    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts which extract from properties file.
     */
    Action(final Connection connection,
           final PropertiesLoader scripts) {

        this.connection = connection;
        this.scripts = scripts;
    }
}
