package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Edition commit.
 */
public class EditionComment extends SQLEvent {

    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public EditionComment(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Edition commit by content old commit.
     *
     * @param oldCommit for replace.
     * @param newCommit for replace.
     * @throws SQLException when PreparedStatement use incorrect.
     */
    public void editionCommit(final String oldCommit, final String newCommit) throws SQLException {

        final String requestPattern = this.requests.generate("edit_commit");


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setString(1, newCommit);

            statement.setString(2, oldCommit);


            statement.executeUpdate();
        }
    }
}
