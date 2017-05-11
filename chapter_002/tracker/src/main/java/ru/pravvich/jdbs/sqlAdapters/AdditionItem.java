package ru.pravvich.jdbs.sqlAdapters;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.models.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Addition item in database.
 */
public class AdditionItem extends SQLEvent {
    /**
     * Default constructor.
     *
     * @param connection decorator for Connection to database.
     * @param requests   generator requests in PostgresSQL style.
     */
    public AdditionItem(ConnectionReactor connection, Generator requests) {
        super(connection, requests);
    }

    /**
     * Add item in table 'tasks'.
     * @param item for adding.
     * @throws SQLException if with PreparedStatement object something wrong.
     */
    public void addItem(final Item item) throws SQLException {

        final String requestPattern = this.requests.generate("add_item");

        if (item == null) throw new IllegalArgumentException();


        try (final PreparedStatement statement =

                     this.connection.get()

                             .prepareStatement(requestPattern)) {



            // First position = author.
            statement.setString(1, item.getNameUser());

            // Second position = header.
            statement.setString(2, item.getHeader());

            // Treed position = description.
            statement.setString(3, item.getDescription());


            final ResultSet rs = statement.executeQuery();

//            System.out.println(rs.next());
            if (rs.next()) System.out.println("id = " + rs.getInt(1));
        }
    }
}
