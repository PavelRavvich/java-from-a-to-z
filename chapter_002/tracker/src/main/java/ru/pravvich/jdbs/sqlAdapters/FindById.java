package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Find Item by Id in Database.
 */
public class FindById extends SQLEvent {

    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public FindById(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Find item by id in table tasks.
     *
     * @param id for search.
     * @return Item with state from database. Include comments.
     * @throws SQLException if PreparedStatement use incorrect.
     */
    public Item findById(final int id) throws SQLException {

        final Item result = new Item();

        final String requestPattern = this.requests.generate("find_by_id");

        if (id < 1) throw new IllegalArgumentException();


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {


            // Id for find item in table tasks.
            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                result.setCreate(resultSet.getTimestamp("create_time").getTime());
                result.setDescription(resultSet.getString("description"));
                result.getCommits().add(resultSet.getString("content"));
                result.setNameUser(resultSet.getString("author"));
                result.setHeader(resultSet.getString("header"));

            }

            result.setId(id);

        }

        return result;
    }
}
