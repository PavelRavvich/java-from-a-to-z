package ru.pravvich.jdbc.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Timestamp;

public class UserDeleterTest extends TestDatabase {

    @Test
    public void whenDeleteUserByIdThenUserIsDeleted() {

        final User user = new User(1, "test1","test1","test1","test1",
                new Timestamp(System.currentTimeMillis()), "test1");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");

        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        final boolean before = new UserExistChecker(connection, originalScripts).userIsExist("test1", "test1");


        //test.
        final UserDeleter deleter = new UserDeleter(connection, originalScripts);

        deleter.deleteUserBy(1);


        final boolean after = new UserExistChecker(connection, originalScripts).userIsExist("test1", "test1");


        Assert.assertTrue(before);
        Assert.assertFalse(after);
    }


    @Test
    public void whenDeleteByLoginAndPasswordThenUserIsDeleted() {


        final User user = new User(1, "username","login","password","email",
                new Timestamp(System.currentTimeMillis()), "test1");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");

        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        final boolean before = new UserExistChecker(connection, originalScripts).userIsExist("login", "password");


        //test.
        final UserDeleter deleter = new UserDeleter(connection, originalScripts);

        deleter.deleteUserBy(user.getLogin(), user.getPassword());


        final boolean after = new UserExistChecker(connection, originalScripts).userIsExist("login", "password");


        Assert.assertTrue(before);
        Assert.assertFalse(after);


    }

}