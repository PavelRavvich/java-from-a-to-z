package ru.pravvich.jdbc.actions;

import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Getter for all users from database.
 */
public class GetterAllUsers extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public GetterAllUsers(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Get all users which contains in database.
     *
     * @return
     */
    public List<User> getAllUsers() {

        final List<User> users = new ArrayList<>(100);

        try (final PreparedStatement statement =

                     connection.prepareStatement(scripts.get("get_all_users"))) {


            final ResultSet result = statement.executeQuery();


            while (result.next()) {

                final User user =

                        new User(
                                result.getInt("id"),
                                result.getString("username"),
                                result.getString("login"),
                                result.getString("password"),
                                result.getString("email"),
                                result.getTimestamp("create_date"),
                                result.getString("access")
                        );

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
