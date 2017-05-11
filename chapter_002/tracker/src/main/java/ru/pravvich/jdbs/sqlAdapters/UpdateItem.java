package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.models.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateItem extends SQLEvent {
    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public UpdateItem(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    //update tasks set header = (?) where task_id = (?);

    /**
     * Replace header by id Item.
     * @param item item for replace.
     * @throws SQLException if PreparedStatement use incorrect.
     */
    public void updateItem(final Item item) throws SQLException {

        final String requestPattern = this.requests.generate("update_item");

        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setString(1, item.getHeader());

            statement.setInt(2, item.getId());


            statement.executeUpdate();
        }
    }
}
