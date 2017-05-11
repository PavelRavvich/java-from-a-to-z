package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.models.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Add comment for task. Task(DB name) == Item(Java name).
 */
public class AdditionComment extends SQLEvent {

    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public AdditionComment(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Addition comment to task.
     * @param item for comment.
     * @param commit commit for add
     * @throws SQLException if PreparedStatement used is fail.
     */
    public void addCommit(final Item item, final String commit) throws SQLException {

        final String requestPattern = this.requests.generate("add_commit");


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setString(1, commit);

            statement.setInt(2, item.getId());


            statement.executeUpdate();
        }
    }
}
