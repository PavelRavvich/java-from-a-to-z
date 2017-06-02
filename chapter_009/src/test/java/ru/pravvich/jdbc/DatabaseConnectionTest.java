package ru.pravvich.jdbc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;

public class DatabaseConnectionTest {

    private Connection connection;

    @Test
    public void whenCallGetThenGetNewConnectionToDatabase() throws SQLException {
        DatabaseConnection factory = new DatabaseConnection(
                new PropertiesLoader("authentication_database"));

        connection = factory.getConnection();

        Assert.assertNotNull(connection);
        Assert.assertFalse(connection.isClosed());
        Assert.assertThat(connection.getCatalog(), is("user_storage"));
    }

    @After
    public void close() throws SQLException {
        connection.close();
    }


}