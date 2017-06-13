package ru.pravvich.jdbc.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;

public class GetterAllUsersTest extends TestDatabase {

    @Test
    public void whenDatabaseContainsTwoUsersThenAllTwoUsersReturnInList() {

        final User user1 = new User(1, "test1","test1","test1","test1",
                new Timestamp(System.currentTimeMillis()), "test1");

        final User user2 = new User(2, "test2","test2","test2","test2",
                new Timestamp(System.currentTimeMillis()), "test2");

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");

        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user1);
        adder.addUser(user2);

        //test.
        final GetterAllUsers getterAllUsers = new GetterAllUsers(connection, originalScripts);

        final List<User> result = getterAllUsers.getAllUsers();

        Assert.assertThat(result.size(), is(2));
        Assert.assertThat(result.get(0).getName(), is("test1"));
        Assert.assertThat(result.get(1).getName(), is("test2"));
    }

    @Test
    public void whenDatabaseIsEmptyThenReturnEmptyList() {

        final PropertiesLoader originalScripts = new PropertiesLoader("database_scripts");

        //test.
        final GetterAllUsers getterAllUsers = new GetterAllUsers(connection, originalScripts);

        final List<User> result = getterAllUsers.getAllUsers();

        Assert.assertThat(result.size(), is(0));
    }

}