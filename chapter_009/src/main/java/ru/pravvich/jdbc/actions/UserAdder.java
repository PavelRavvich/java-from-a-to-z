package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Adder users to database..
 */
public class UserAdder extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public UserAdder(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public void addUser(final User user) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("add"))) {

            statement.setString(1, user.getName());

            statement.setString(2, user.getLogin());

            statement.setString(3, user.getPassword());

            statement.setString(4, user.getEmail());

            statement.setString(5, user.getSuccessLevel());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Try addition user in DB.
     *
     * @param user for addition.
     * @return true if couple login & password unique, addition successful.
     * If couple login&password NOT unique return false.
     */
    public boolean addAndGetResult(final User user) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("add_and_get"))) {

            statement.setString(1, user.getName());

            statement.setString(2, user.getLogin());

            statement.setString(3, user.getPassword());

            statement.setString(4, user.getEmail());

            statement.setString(5, user.getSuccessLevel());


            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
