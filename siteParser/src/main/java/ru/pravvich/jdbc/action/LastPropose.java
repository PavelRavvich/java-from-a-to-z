package ru.pravvich.jdbc.action;

import ru.pravvich.parser.PropertiesLoader;

import java.sql.*;

/**
 * Get datetime of last write in propose table.
 */
public class LastPropose extends DatabaseAction {

    /**
     * @see DatabaseAction#DatabaseAction(PropertiesLoader, Connection)
     */
    public LastPropose(final PropertiesLoader properties,
                       final Connection connection) {

        super(properties, connection);
    }

    /**
     * Get datetime of last write in propose table in Timestamp object.
     *
     * @return time of last propose.
     */
    public Timestamp getTimeLastPropose() {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.getValue("last_date"))
        ) {


            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            final Timestamp time = resultSet.getTimestamp(1);

            if (time != null) return time;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Timestamp(0);
    }
}
