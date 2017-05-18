package ru.pravvich.jdbc.action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirstStartCheckerTest {

    private Connection connection;
    private PropertiesLoader testProp;
    private PropertiesLoader properties;

    private void initProperties() {
        properties = new PropertiesLoader("DBAction");
        testProp = new PropertiesLoader("testDatabase");
    }

    private void initConnectionToRoot() {
        try {
            connection = DriverManager.getConnection(
                    testProp.getValue("url_database"),
                    testProp.getValue("login_parser"),
                    testProp.getValue("password_parser"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void createTableRecruiter() {
        try (final PreparedStatement statement = connection.prepareStatement(
                testProp.getValue("create_recruiter_test"))) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeEachTest() {
        initProperties();
        initConnectionToRoot();
        createTableRecruiter();
    }

    @Test
    public void whenRecruiterTableIsNotEmptyThenReturnFalse() {

        final Proposal proposal = new Proposal();
        proposal.setUrlRecruiter("url");
        proposal.setNickname("name");

        new InjectorInRecruiter(properties, connection).injectInRecruiter(proposal);


        final FirstStartChecker checker = new FirstStartChecker(properties, connection);

        final boolean result = checker.isFirstStart();

        assertFalse(result);
    }

    @Test
    public void whenRecruiterTableIsEmptyThenReturnTrue() {

        final FirstStartChecker checker = new FirstStartChecker(properties, connection);

        final boolean result = checker.isFirstStart();

        assertTrue(result);
    }

    @After
    public void afterEachTest() throws SQLException {
        deleteRecruiterTable();
        closeConnection();
    }

    private void deleteRecruiterTable() {

        final String request = testProp.getValue("delete_recruiter_test");

        try (final PreparedStatement statement = connection.prepareStatement(request)) {

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