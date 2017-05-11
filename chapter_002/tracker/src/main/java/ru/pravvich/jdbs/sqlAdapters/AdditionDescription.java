package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Addition description in task which exist in database.
 */
public class AdditionDescription extends SQLEvent {

    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public AdditionDescription(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Add description in exist task(Item).
     * @param id tasks for update.
     * @param description for addition.
     * @throws SQLException then PreparedStatement use incorrect.
     */
    public void addDescription(final int id, final String description) throws SQLException {

        final String requestPattern = this.requests.generate("add_description");


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setString(1, description);

            statement.setInt(2, id);


            statement.executeUpdate();
        }
    }
}
