package ru.pravvich.jdbc.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;

public class SingleUserGetterTest extends TestDatabase {

    @Test
    public void whenGetUserByIdWhichExistThenGetUser() {
        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");
        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        //test.
        SingleUserGetter getter = new SingleUserGetter(connection, originalScripts);

        final User result = getter.getUserBy(1);

        Assert.assertThat(result.getName(), is("test"));
    }


    @Test
    public void whenGetUserByLoginAndPasswordCallThenUserWithSameLoginAndPasswordReturn() {
        final User user = new User(1, "name","login","password","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");
        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        //test.
        SingleUserGetter getter = new SingleUserGetter(connection, originalScripts);

        final User result = getter.getUserBy("login", "password");
        Assert.assertThat(result.getName(), is("name"));
    }

}