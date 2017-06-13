package ru.pravvich.jdbc.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Timestamp;

public class UserExistCheckerTest extends TestDatabase {

    @Test
    public void whenUserExistInDatabaseByLoginPasswordThenReturnTrue() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");
        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        final UserExistChecker checker = new UserExistChecker(connection, originalScripts);
        final boolean result = checker.userIsExist("test", "test");

        Assert.assertTrue(result);
    }


    @Test
    public void whenUserNotExistInDatabaseByLoginPasswordThenReturnFalse() {

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");


        final UserExistChecker checker = new UserExistChecker(connection, originalScripts);
        final boolean result = checker.userIsExist("test", "test");

        Assert.assertFalse(result);
    }


    @Test
    public void whenUserExistInDatabaseByIdThenReturnTrue() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");
        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        final UserExistChecker checker = new UserExistChecker(connection, originalScripts);
        final boolean result = checker.userIsExist(1);

        Assert.assertTrue(result);
    }

    @Test
    public void whenUserNotExistInDatabaseByIdThenReturnFalse() {

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");


        final UserExistChecker checker = new UserExistChecker(connection, originalScripts);
        final boolean result = checker.userIsExist("test", "test");

        Assert.assertFalse(result);
    }
}