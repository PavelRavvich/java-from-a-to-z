package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;

/**
 * Determines states all SQL events class.
 */
public class SQLEvent {
    /**
     * Decorator for Connection. Method get() return open Connection.
     */
    protected ConnectionReactor connection;
    /**
     * Generate requests pattern by key.
     */
    protected final Generator requests;

    /**
     * Default constructor.
     * @param connection decorator for Connection to database.
     * @param requests generator requests in PostgresSQL style.
     */
    public SQLEvent(final ConnectionReactor connection,
                    final Generator requests) {

        this.connection = connection;
        this.requests = requests;
    }
}
