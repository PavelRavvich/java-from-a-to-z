package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Find Item by Header.
 */
public class FindByHeader extends SQLEvent {

    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public FindByHeader(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Find Item by header.
     *
     * @param header which have find Item.
     * @return Item which contain header.
     * @throws SQLException if PreparedStatement use incorrect.
     */
    public Item findByHeader(final String header) throws SQLException {

        final String requestPattern = this.requests.generate("find_by_header");


        final Item result = new Item();

        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            statement.setString(1, header);

            final ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {

                result.setCreate(resultSet.getTimestamp("create_time").getTime());
                result.setDescription(resultSet.getString("description"));
                result.setId(resultSet.getInt("task_id"));
                result.setNameUser(resultSet.getString("author"));
                result.getCommits().add(resultSet.getString("content"));

            }

            result.setHeader(header);
        }

        return result;
    }
}
