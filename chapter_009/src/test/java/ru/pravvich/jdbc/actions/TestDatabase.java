package ru.pravvich.jdbc.actions;

import org.junit.After;
import org.junit.Before;
import ru.pravvich.jdbc.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Create test database before each test and delete database after each test.
 */
public class TestDatabase {

    protected Connection connection;
    private PropertiesLoader properties;

    private void initProperties() {
        properties = new PropertiesLoader("test_database");
    }

    private void openConnection() {
        try {

            connection = DriverManager.getConnection(
                    properties.get("url"),
                    properties.get("username"),
                    properties.get("password")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (final PreparedStatement statement =
                     connection.prepareStatement(
                             properties.get("create_table")
                     )
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void before() {
        initProperties();
        openConnection();
        createTable();
    }


    @After
    public void after() {
        deleteTable();
        closeConnection();
    }

    private void deleteTable() {
        try (final PreparedStatement statement =
                     connection.prepareStatement(
                             properties.get("drop_table")
                     )
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
