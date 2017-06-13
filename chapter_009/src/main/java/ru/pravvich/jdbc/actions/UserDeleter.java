package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Deleter users from database.
 */
public class UserDeleter extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public UserDeleter(final Connection connection, final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public void deleteUserBy(final int id) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("del_user_by_id"))) {


            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserBy(final String login, final String password) {

        try (final PreparedStatement statement =

                    connection.prepareStatement(

                            scripts.get("del_user_by_log_pas"))
        ) {


            statement.setString(1, login);

            statement.setString(2, password);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
