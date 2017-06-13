package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Getter for one user by id.
 */
public class SingleUserGetter extends Action {

    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public SingleUserGetter(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Get user from database by id.
     *
     * @return if User not found return empty User object, else normal User from DB.
     */
    public User getUserBy(final int id) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("get_by_id"))) {


            statement.setInt(1, id);


            final ResultSet result = statement.executeQuery();

            if (result.next()) {

                return new User(
                        id,
                        result.getString("username"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getTimestamp("create_date"),
                        result.getString("success_level"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User();
    }

    public User getUserBy(final String login, final String password) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("get_by_login_password"))) {


            statement.setString(1, login);
            statement.setString(2, password);

            final ResultSet result = statement.executeQuery();

            if (result.next()) {

                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        login,
                        password,
                        result.getString("email"),
                        result.getTimestamp("create_date"),
                        result.getString("success_level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User();
    }
}
