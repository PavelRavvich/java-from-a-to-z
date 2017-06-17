package ru.pravvich.jdbc.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.PropertiesLoader;
import ru.pravvich.user.User;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;

public class AccessLevelGetterTest extends TestDatabase {

    @Test
    public void whenUserIsExistThenAccessLevelReturnAsString() {
        final User user = new User(1, "test","test","test","test",
                new Timestamp(System.currentTimeMillis()), "test");

        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final UserAdder adder = new UserAdder(connection, originalScripts);

        adder.addUser(user);


        final AccessLevelGetter access
                = new AccessLevelGetter(connection, originalScripts);

        final String result = access.getAccess("test", "test");

        Assert.assertThat(result, is("test"));
    }

    @Test
    public void whenUserNotExistThenReturnFlag() {
        final PropertiesLoader originalScripts =
                new PropertiesLoader("database_scripts");

        final AccessLevelGetter access
                = new AccessLevelGetter(connection, originalScripts);

        final String result = access.getAccess("test", "test");

        Assert.assertThat(result, is("not_found"));
    }
}