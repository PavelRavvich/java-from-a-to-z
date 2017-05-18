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

public class RecruiterExistCheckerTest {

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
        addRecruiter();
    }

    private void addRecruiter() {

        final Proposal proposal = new Proposal();
        proposal.setUrlRecruiter("url");
        proposal.setNickname("author");

        new InjectorInRecruiter(properties, connection).injectInRecruiter(proposal);
    }

    @Test
    public void whenRecruiterExistThenReturnTrue() {

        final RecruiterExistChecker re = new RecruiterExistChecker(properties, connection);

        final boolean result = re.recruiterIsExist("author");

        assertTrue(result);
    }

    @Test
    public void whenRecruiterNotExistThenReturnFalse() {

        final RecruiterExistChecker re = new RecruiterExistChecker(properties, connection);

        final boolean result = re.recruiterIsExist("not exist recruiter nickname");

        assertFalse(result);
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