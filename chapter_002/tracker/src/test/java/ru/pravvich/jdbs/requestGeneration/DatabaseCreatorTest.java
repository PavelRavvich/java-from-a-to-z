package ru.pravvich.jdbs.requestGeneration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.pravvich.jdbs.PropertyLoader;
import ru.pravvich.jdbs.StubPropertyLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;

public class DatabaseCreatorTest {

    private static Connection rootConnection;

    private final PropertyLoader property = new StubPropertyLoader();

    @BeforeClass
    public static void initRootConnection() {
        final PropertyLoader property = new StubPropertyLoader();

        try {

            rootConnection = DriverManager.getConnection(
                    property.getRootDB(),
                    property.getUsername(),
                    property.getPassword());


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void whenDatabaseIsExistsThenReturnTrue() throws SQLException {

        final DatabaseCreator creator = new DatabaseCreator();

        creator.openConnection(this.property);

        final boolean result = creator.existDB("template0");

        creator.closeConnection();

        Assert.assertThat(result, is(true));

    }

    @Test
    public void whenDatabaseNotExistsThenReturnFalse() throws SQLException {

        final DatabaseCreator creator = new DatabaseCreator();

        creator.openConnection(this.property);

        final boolean result = creator.existDB("database_which_not_exist");

        creator.closeConnection();

        Assert.assertThat(result, is(false));

    }

    @Test
    public void whenCreateDatabase() throws SQLException {

        final DatabaseCreator creator = new DatabaseCreator();

        creator.openConnection(this.property);

        final boolean before = creator.existDB("database_for_test");

        creator.createDB("database_for_test");

        final boolean after = creator.existDB("database_for_test");

        creator.closeConnection();

        Assert.assertThat(before, is(false));
        Assert.assertThat(after, is(true));

    }

    @AfterClass
    public static void closeAllResources() {

        try {

            deleteTestDatabase();

            rootConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteTestDatabase() throws SQLException {

        final PreparedStatement statement =

                rootConnection.prepareStatement(

                        "DROP DATABASE database_for_test");


        statement.executeUpdate();

    }
}