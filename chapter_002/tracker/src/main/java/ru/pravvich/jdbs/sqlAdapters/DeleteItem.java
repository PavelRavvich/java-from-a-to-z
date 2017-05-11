package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.models.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Delete Item from database.
 */
public class DeleteItem extends SQLEvent {
    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public DeleteItem(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Delete task with all comments.
     * @param item for delete
     * @throws SQLException if PreparedStatement use incorrect.
     */
    public void delete(final Item item) throws SQLException {

        final String requestPattern = this.requests.generate("delete_item");

        if (requestPattern.equals("-1")) return;


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setInt(1, item.getId());


            statement.executeUpdate();
        }
    }
}
