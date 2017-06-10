package ru.pravvich.jdbc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.user.User;

import java.sql.*;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class ScriptExecutorTest {

    private Connection connection;
    private PropertiesLoader properties;

    private void initProperties() {
        properties = new PropertiesLoader("test_database");
    }

    private void openConnection() {
        try {

            connection = DriverManager.getConnection(
                    properties.get("url"),
                    properties.get("username"),
                    properties.get("password")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (final PreparedStatement statement =
                     connection.prepareStatement(
                             properties.get("create_table")
                     )
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void before() {
        initProperties();
        openConnection();
        createTable();
    }



    @Test
    public void whenCallAddUserAndCallGetUserThenReturnSameUser() {
        final ScriptExecutor scriptExecutor = new ScriptExecutor(
                connection, new PropertiesLoader("database_scripts"));

        final User user =  new User(
                0, "name", "login", "email",
                new Timestamp(System.currentTimeMillis()));

        scriptExecutor.addUser(user);

        final User result = scriptExecutor.getUser(1);

        Assert.assertThat(result.getEmail(), is(user.getEmail()));
    }

    @Test
    public void whenDeleteUserThenUserIsDeleted() {

        final ScriptExecutor scriptExecutor = new ScriptExecutor(
                connection, new PropertiesLoader("database_scripts"));

        final User user =  new User(
                1, "name", "login", "email",
                new Timestamp(System.currentTimeMillis()));

        scriptExecutor.addUser(user);

        final User before = scriptExecutor.getUser(1);

        //Test.
        scriptExecutor.deleteUser(user);

        final User after = scriptExecutor.getUser(1);

        Assert.assertNotNull(before.getEmail());
        Assert.assertNull(after.getEmail());
    }

    @Test
    public void whenUserUpdateThenNameLoginAndEmailUpdated() {

        final ScriptExecutor scriptExecutor = new ScriptExecutor(
                connection, new PropertiesLoader("database_scripts"));

        final User oldVersion =  new User(
                1, "name", "login", "email",
                new Timestamp(System.currentTimeMillis()));

        scriptExecutor.addUser(oldVersion);



        final User newVersion = new User(
                1, "new_name", "new_login", "new_email",
                new Timestamp(System.currentTimeMillis()));

        //Test.
        scriptExecutor.updateUser(newVersion);


        final User result = scriptExecutor.getUser(1);

        Assert.assertThat(result.getEmail(), is(newVersion.getEmail()));
    }


    @Test
    public void whenGetAllUserCallThenReturnAllUserWhichExistInTableUsers() {

        final ScriptExecutor scriptExecutor = new ScriptExecutor(
                connection, new PropertiesLoader("database_scripts"));

        final User user1 =  new User(
                0, "user1", "login1", "email1",
                new Timestamp(System.currentTimeMillis()));

        final User user2 =  new User(
                0, "user2", "login2", "email2",
                new Timestamp(System.currentTimeMillis()));

        scriptExecutor.addUser(user1);
        scriptExecutor.addUser(user2);

        // test.
        final List<User> result = scriptExecutor.getAllUsers();

        Assert.assertThat(result.get(0).getName(), is("user1"));
        Assert.assertThat(result.get(1).getName(), is("user2"));
    }


    @Test
    public void whenUserExistInDBThenCallUserIsExistMethodReturnTrue() {

        final ScriptExecutor scriptExecutor = new ScriptExecutor(
                connection, new PropertiesLoader("database_scripts"));

        final User user =  new User(
                0, "user", "login", "email",
                new Timestamp(System.currentTimeMillis()));

        scriptExecutor.addUser(user);


        //test.
        final boolean result = scriptExecutor.userIsExist(user);
        Assert.assertTrue(result);
    }


    @Test
    public void whenUserNotExistInDBThenCallUserIsExistMethodReturnFalse() {

        final ScriptExecutor scriptExecutor = new ScriptExecutor(
                connection, new PropertiesLoader("database_scripts"));

        final User user =  new User(
                0, "user", "login", "email",
                new Timestamp(System.currentTimeMillis()));


        //test.
        final boolean result = scriptExecutor.userIsExist(user);
        Assert.assertFalse(result);
    }


    @After
    public void after() {
        deleteTable();
        closeConnection();
    }

    private void deleteTable() {
        try (final PreparedStatement statement =
                     connection.prepareStatement(
                             properties.get("drop_table")
                     )
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}