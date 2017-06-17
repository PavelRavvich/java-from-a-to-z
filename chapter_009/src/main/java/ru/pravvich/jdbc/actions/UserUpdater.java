package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Updater user.
 */
public class UserUpdater extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public UserUpdater(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Update user by id.
     *
     * @param id           which retrieve new state
     * @param newUserState contains new state for user.
     */
    public void updateUserById(final int id, final User newUserState) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("update"))) {


            statement.setString(1, newUserState.getName());
            statement.setString(2, newUserState.getLogin());
            statement.setString(3, newUserState.getPassword());
            statement.setString(4, newUserState.getEmail());
            statement.setString(5, newUserState.getSuccessLevel());
            statement.setInt(6, id);


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateAndGet(final int id, final User newUserState) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("update_and_get"))) {


            statement.setString(1, newUserState.getName());
            statement.setString(2, newUserState.getLogin());
            statement.setString(3, newUserState.getPassword());
            statement.setString(4, newUserState.getEmail());
            statement.setString(5, newUserState.getSuccessLevel());
            statement.setInt(6, id);
            statement.setString(7, newUserState.getLogin());
            statement.setString(8, newUserState.getPassword());


            final ResultSet set = statement.executeQuery();

            set.next();

            return set.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
