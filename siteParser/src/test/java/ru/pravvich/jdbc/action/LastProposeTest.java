package ru.pravvich.jdbc.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;

public class LastProposeTest {

    private Proposal proposal;
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

        Calendar calendar = new GregorianCalendar(2010, 0, 0, 0, 0, 0);

        proposal.setUrlRecruiter("url_recruiter");
        proposal.setUlrPropose("url_propose");
        proposal.setNickname("author");
        proposal.setHeader("header");
        proposal.setCreate(new Timestamp(calendar.getTimeInMillis()));
        new InjectorInRecruiter(properties, connection)
                .injectInRecruiter(proposal);
    }

    private void addToProposeTable() throws SQLException {

        new InjectorInProposal(properties, connection)
                .injectInProposal(proposal);
    }


    @Before
    public void beforeEachTest() throws SQLException {
        proposal = new Proposal();
        initProperties();
        initConnectionToRoot();
        createTableRecruiter();
        createTableProposal();
        addToRecruiterTable();
        addToProposeTable();
    }

    @Test
    public void whenCallGetTimeLastProposeThenReturnTimestampWithDatetime() {

        final LastPropose lp = new LastPropose(properties, connection);

        final Timestamp result = lp.getTimeLastPropose();

        Assert.assertThat(result, is(
                new Timestamp(
                        new GregorianCalendar(2010, 0, 0, 0, 0, 0)
                                .getTimeInMillis())));
    }

    @Test
    public void whenCallGetTimeLastProposeThenReturnTimestampWithLastDatetime() throws SQLException {

        final Timestamp sample =
                new Timestamp(new GregorianCalendar(
                        2017, 1, 1, 1, 1, 1).getTimeInMillis());


        final Proposal proposal = new Proposal();
        proposal.setCreate(new Timestamp(new GregorianCalendar(
                2017, 1, 1, 1, 1, 1).getTimeInMillis()));

        proposal.setUrlRecruiter("url_recruiter");
        proposal.setUlrPropose("r_url");
        proposal.setNickname("author");
        proposal.setHeader("hed");

        new InjectorInProposal(properties, connection).injectInProposal(proposal);

        final LastPropose lp = new LastPropose(properties, connection);

        final Timestamp result = lp.getTimeLastPropose();

        Assert.assertThat(result, is(sample));
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