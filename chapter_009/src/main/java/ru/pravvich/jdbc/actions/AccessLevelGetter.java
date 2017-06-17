package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Getter user's access level.
 */
public class AccessLevelGetter extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public AccessLevelGetter(final Connection connection,
                             final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Get user's access level.
     *
     * @return column access or flag "not_found" if user not exist.
     */
    public String getAccess(final String login, final String password) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("success_level")

                     )

        ) {

            statement.setString(1, login);

            statement.setString(2, password);


            final ResultSet set = statement.executeQuery();

            if (set.next()) return set.getString(1).trim();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "not_found";
    }
}
