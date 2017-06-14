package ru.pravvich.jdbc;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.actions.TestDatabase;
import ru.pravvich.user.User;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class ScriptExecutorTest extends TestDatabase {

    @Test
    public void whenDatabaseContainsTwoUsersThenAllTwoUsersReturnInList() {

        final User user1 = new User(1, "test1","test1","test1","test1",
                new Timestamp(System.currentTimeMillis()), "test1");

        final User user2 = new User(2, "test2","test2","test2","test2",
                new Timestamp(System.currentTimeMillis()), "test2");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user1);
        executor.addUser(user2);

        //test.
        final List<User> result = executor.getAllUsers();

        Assert.assertThat(result.size(), is(2));
        Assert.assertThat(result.get(0).getName(), is("test1"));
        Assert.assertThat(result.get(1).getName(), is("test2"));
    }

    @Test
    public void whenDatabaseIsEmptyThenReturnEmptyList() {

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        //test.
        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        final List<User> result = executor.getAllUsers();

        Assert.assertThat(result.size(), is(0));
    }

    @Test
    public void whenGetUserByIdWhichExistThenGetUser() {
        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);

        //test.
        final User result = executor.getUserById(1);

        Assert.assertThat(result.getName(), is("test"));
    }


    @Test
    public void whenGetUserByLoginAndPasswordCallThenSameUserReturn() {
        final User user = new User(1, "name","login","password","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);

        //test.
        final User result = executor
                .getUserByLoginPassword("login", "password");

        Assert.assertThat(result.getName(), is("name"));
    }


    @Test
    public void whenUserAdditionSuccessThenUserExistInDatabase() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);

        final boolean result = executor.userIsExist("test", "test");

        Assert.assertTrue(result);
    }


    @Test
    public void whenDeleteUserByIdThenUserIsDeleted() {

        final User user = new User(1, "test1","test1","test1","test1",
                new Timestamp(System.currentTimeMillis()), "test1");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);


        final boolean before = executor.userIsExist("test1", "test1");


        //test.

        executor.deleteUserById(1);

        final boolean after = executor.userIsExist("test1", "test1");

        Assert.assertTrue(before);
        Assert.assertFalse(after);
    }


    @Test
    public void whenDeleteByLoginAndPasswordThenUserIsDeleted() {

        final User user = new User(1, "username","login","password","email",
                new Timestamp(System.currentTimeMillis()), "test1");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);

        final boolean before = executor.userIsExist("login", "password");


        //test.

        executor.deleteUserByLoginPassword(user.getLogin(), user.getPassword());


        final boolean after = executor.userIsExist("login", "password");

        Assert.assertTrue(before);
        Assert.assertFalse(after);
    }


    @Test
    public void whenUserExistInDatabaseByLoginPasswordThenReturnTrue() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);


        final boolean result = executor.userIsExist("test", "test");

        Assert.assertTrue(result);
    }


    @Test
    public void whenUserNotExistInDatabaseByLoginPasswordThenReturnFalse() {

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        final boolean result = executor.userIsExist("test", "test");

        Assert.assertFalse(result);
    }


    @Test
    public void whenUserExistInDatabaseByIdThenReturnTrue() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);


        final boolean result = executor.userIsExist(1);

        Assert.assertTrue(result);
    }

    @Test
    public void whenUserNotExistInDatabaseByIdThenReturnFalse() {

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        final boolean result = executor.userIsExist("test", "test");

        Assert.assertFalse(result);
    }

    @Test
    public void whenUserUpdateThenUserUpdated() {

        final User user = new User(0, "username","login","password","email",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        executor.addUser(user);

        //test.

        final User updatedUser = new User(
                0, "new_username","new_login", "new_password","new_email",
                new Timestamp(System.currentTimeMillis()), "test");


        //get user before update.
        final User before = executor.getUserById(1);

        //tested method.
        executor.updateUser(1, updatedUser);

        //get user after update.
        final User after = executor.getUserById(1);


        Assert.assertThat(before.getName(), is("username"));
        Assert.assertThat(after.getName(), is("new_username"));

    }

    @Test
    public void whenPairLoginPasswordIsUniqueThenReturnTrue() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);

        final boolean result = executor.addUserAndGetSuccess(user);

        Assert.assertTrue(result);
    }

    @Test
    public void whenPairLoginAlreadyExistInDBPasswordThenReturnFalse() {


        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final ScriptExecutor executor =
                new ScriptExecutor(connection, originalScripts);


        executor.addUserAndGetSuccess(user);
        final boolean result = executor.addUserAndGetSuccess(user);

        Assert.assertFalse(result);

    }
}