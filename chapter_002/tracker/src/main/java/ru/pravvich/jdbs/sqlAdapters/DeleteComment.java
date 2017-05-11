package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Delete comment from database.
 */
public class DeleteComment extends SQLEvent {
    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public DeleteComment(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Deleted comment.
     *
     * @param commit value for delete.
     * @throws SQLException if PreparedStatement use incorrect.
     */
    public void deleteCommit(final String commit) throws SQLException {

        final String requestPattern = this.requests.generate("delete_commit");


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setString(1, commit);

            statement.executeUpdate();
        }
    }
}
