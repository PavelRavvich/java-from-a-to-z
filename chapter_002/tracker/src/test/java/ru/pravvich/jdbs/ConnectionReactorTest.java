package ru.pravvich.jdbs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;

public class ConnectionReactorTest {
    private static PropertyLoader property;
    private static Connection root;

    @Before
    public void init() throws SQLException {
        property = new StubPropertyLoader();

        root = DriverManager.getConnection(
                property.getRootDB(),
                property.getUsername(),
                property.getPassword());

        try (final PreparedStatement createDB =
                     root.prepareStatement("CREATE DATABASE test_auto_services")
        ) {


            createDB.executeUpdate();
        }
    }

    @Test
    public void whenCallGetThenReturnNotNull() throws SQLException {

        final ConnectionReactor reactor = new ConnectionReactor(property);

        final Connection result = reactor.get();

        result.close();

        Assert.assertNotNull(result);
    }

    @Test
    public void whenCallGetThenBeforeCallCloseConnectionIsOpen() throws SQLException {

        final ConnectionReactor reactor = new ConnectionReactor(property);

        final Connection connection = reactor.get();

        final boolean result = connection.isClosed();

        connection.close();

        Assert.assertFalse(result);
    }

    @Test
    public void whenCallGetThenCatalogIsTargetDatabase() throws SQLException {

        final ConnectionReactor reactor = new ConnectionReactor(property);

        final Connection connection = reactor.get();

        final String result = connection.getCatalog();

        connection.close();

        Assert.assertThat(result, is("test_auto_services"));

    }

    @Test
    public void whenCallCloseConnectionThenConnectionIsClose() throws SQLException {

        final ConnectionReactor reactor = new ConnectionReactor(property);

        final Connection connection = reactor.get();

        reactor.closeConnection();

        final boolean result = connection.isClosed();

        Assert.assertTrue(result);
    }

    @After
    public void cleanResources() {
        try {

            deleteTestDatabase();

            root.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestDatabase() throws SQLException {

        root.prepareStatement("DROP DATABASE test_auto_services").executeUpdate();

    }
}