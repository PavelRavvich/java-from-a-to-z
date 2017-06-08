package ru.pravvich.jdbc;

import ru.pravvich.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * All transactions to database.
 */
public class ScriptExecutor {
    /**
     * Connection to database.
     */
    private final Connection connection;
    /**
     * Requests loader from file Properties.
     */
    private final PropertiesLoader properties;

    /**
     * Default constructor.
     *
     * @param connection to database.
     * @param properties requests loader from file Properties.
     */
    public ScriptExecutor(final Connection connection,
                          final PropertiesLoader properties) {

        this.connection = connection;
        this.properties = properties;
    }

    /**
     * Add user in database.
     *
     * @param user for addition.
     */
    public void addUser(final User user) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.get("add"))
        ) {


            statement.setString(1, user.getName());

            statement.setString(2, user.getLogin());

            statement.setString(3, user.getEmail());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete user from users table by id.
     *
     * @param user for delete.
     */
    public void deleteUser(final User user) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.get("delete"))
        ) {


            statement.setInt(1, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user from table users.
     *
     * @param id of user.
     * @return User with param id.
     */
    public User getUser(final int id) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.get("get"))
        ) {


            statement.setInt(1, id);


            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new User(
                        id,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getTimestamp(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new User();
    }

    /**
     * Update user.
     *
     * @param user for update.
     */
    public void updateUser(final User user) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.get("update"))
        ) {

            statement.setInt(4, user.getId());


            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all user which exists in database.
     *
     * @return all user.
     */
    public List<User> getAllUsers() {

        final List<User> allUsers = new ArrayList<>();

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.get("getAll"))
        ) {


            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                allUsers.add(
                        new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getTimestamp(5))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allUsers;

    }
}
