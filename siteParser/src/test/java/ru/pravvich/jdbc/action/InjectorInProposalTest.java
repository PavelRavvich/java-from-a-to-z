package ru.pravvich.jdbc.action;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InjectorInProposalTest {

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

    private void createTableProposal() {
        try (final PreparedStatement statement = connection.prepareStatement(
                testProp.getValue("create_proposal_test"))) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addToRecruiterTable() {
        final Proposal proposal = new Proposal();
        proposal.setUrlRecruiter("url_recruiter");
        proposal.setNickname("author");
        new InjectorInRecruiter(properties, connection)
                .injectInRecruiter(proposal);
    }


    @Before
    public void beforeEachTest() {
        initProperties();
        initConnectionToRoot();
        createTableRecruiter();
        createTableProposal();
        addToRecruiterTable();
    }

    @Test
    public void whenAdditionInProposalTableThenTableContainsCartage() throws SQLException {

        final Proposal proposal = new Proposal();
        proposal.setUrlRecruiter("url_recruiter");
        proposal.setUlrPropose("url_propose");
        proposal.setHeader("header");
        proposal.setNickname("author");
        proposal.setCreate(new Timestamp(System.currentTimeMillis()));


        final InjectorInProposal injector = new InjectorInProposal(properties, connection);

        //Testing ingection.
        injector.injectInProposal(proposal);

        //Check ingection.
        String result = new InjectorInProposalTest.Getter().getProposalURL("header");

        assertThat(result, is("url_propose"));
    }

    class Getter {

        private String getProposalURL(final String header) {

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 properties.getValue("get_url_propose_by_header"))
            ) {

                statement.setString(1, header);

                final ResultSet resultSet = statement.executeQuery();
                resultSet.next();

                return resultSet.getString(1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return "";
        }
    }


    @After
    public void afterEachTest() throws SQLException {
        deleteProposalTable();
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

    private void deleteProposalTable() {

        final String request = testProp.getValue("delete_proposal_test");

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