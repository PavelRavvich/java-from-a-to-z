package ru.pravvich.jdbc.action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InjectorInRecruiterTest {

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
    public void whenAdditionInRecruiterTableThenTableContainsCartage() throws SQLException {

        final Proposal proposal = new Proposal();
        proposal.setUrlRecruiter("url_recruiter");
        proposal.setUlrPropose("url_propose");
        proposal.setHeader("header");
        proposal.setNickname("author");
        proposal.setCreate(new Timestamp(System.currentTimeMillis()));


        final InjectorInRecruiter injector = new InjectorInRecruiter(properties, connection);

        //Testing ingection.
        injector.injectInRecruiter(proposal);

        //Check ingection.
        String result = new Getter().getRecruiterURL("author");

        assertThat(result, is("url_recruiter"));
    }

    class Getter {

        private String getRecruiterURL(final String nickname) {

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 properties.getValue("get_recruiter_by_username"))
            ) {

                statement.setString(1, nickname);

                final ResultSet resultSet = statement.executeQuery();
                resultSet.next();

                return resultSet.getString(2);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return "";
        }
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