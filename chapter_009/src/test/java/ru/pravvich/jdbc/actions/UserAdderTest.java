package ru.pravvich.jdbc.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Timestamp;

public class UserAdderTest extends TestDatabase {

    @Test
    public void whenUserAdditionSuccessThenUserExistInDatabase() {

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
    public void whenPairLoginPasswordIsUniqueThenReturnTrue() {

        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");
        final UserAdder adder = new UserAdder(connection, originalScripts);

        final boolean result = adder.addAndGetResult(user);

        Assert.assertTrue(result);
    }

    @Test
    public void whenPairLoginAlreadyExistInDBPasswordThenReturnFalse() {


        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");
        final UserAdder adder = new UserAdder(connection, originalScripts);


        adder.addAndGetResult(user);
        final boolean result = adder.addAndGetResult(user);

        Assert.assertFalse(result);

    }
}