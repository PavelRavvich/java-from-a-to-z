package ru.pravvich.jdbc;

import ru.pravvich.jdbc.actions.*;
import ru.pravvich.user.User;

import java.sql.Connection;
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
     */
    public void addUser(final User user) {
        new UserAdder(connection, properties).addUser(user);
    }

    /**
     * Delete user from users table by id.
     */
    public void deleteUserById(final int id) {
        new UserDeleter(connection, properties).deleteUserBy(id);

    }

    /**
     * Delete user by login-password set.
     */
    public void deleteUserByLoginPassword(final String login, final String password) {
        new UserDeleter(connection, properties).deleteUserBy(login, password);
    }

    /**
     * Get user by id.
     */
    public User getUserById(final int id) {
        return new SingleUserGetter(connection, properties).getUserBy(id);
    }

    /**
     * Get user by login-password set.
     */
    public User getUserByLoginPassword(final String login, final String password) {
        return new SingleUserGetter(connection, properties).getUserBy(login, password);
    }

    /**
     * Update user by id.
     *
     * @param id user for update.
     * @param user object contains new state for user in database.
     */
    public void updateUser(final int id, final User user) {
        new UserUpdater(connection, properties).updateUserById(id, user);
    }

    /**
     * Get List of all user which exists in database.
     */
    public List<User> getAllUsers() {
        return new GetterAllUsers(connection, properties).getAllUsers();
    }

    /**
     * Check exist user in database by id.
     *
     * @return true if user exist in DB, else false.
     */
    public boolean userIsExist(final int id) {
        return new UserExistChecker(connection, properties).userIsExist(id);
    }

    /**
     * Check exist user in database by login password set.
     *
     * @return true if user exist in DB, else false.
     */
    public boolean userIsExist(final String login, final String password) {
        return new UserExistChecker(connection, properties).userIsExist(login, password);
    }
}
