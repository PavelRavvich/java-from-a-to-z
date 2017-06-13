package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Check exist user in database.
 */
public class UserExistChecker extends Action {

    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public UserExistChecker(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Checking exist user by login and password set.
     *
     * @return true if user exist in DB, else false.
     */
    public boolean userIsExist(final String login, final String password) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("exist"))) {


            statement.setString(1, login);
            statement.setString(2, password);


            final ResultSet result = statement.executeQuery();

            result.next();

            return result.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Check exist user by id.
     *
     * @return true if user exist in DB, else false.
     */
    public boolean userIsExist(final int id) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("exist_id"))) {

            statement.setInt(1, id);

            final ResultSet result = statement.executeQuery();

            result.next();

            return result.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
