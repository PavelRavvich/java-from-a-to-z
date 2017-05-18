package ru.pravvich.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.jdbc.action.InjectorInProposal;
import ru.pravvich.jdbc.action.InjectorInRecruiter;
import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.*;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JDBCChannelTest {

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

    private void addToProposeTable(int year, int month, int day, int hour, int min, int sec, String proposalUrl, String header) {
        final Proposal proposal = new Proposal();
        proposal.setCreate(new Timestamp(new GregorianCalendar(
                        year, month, day, hour, min, sec).getTimeInMillis()));

        proposal.setUrlRecruiter("url_recruiter");
        proposal.setUlrPropose(proposalUrl);
        proposal.setNickname("author");
        proposal.setHeader(header);

        new InjectorInProposal(properties, connection).injectInProposal(proposal);
    }

    @Before
    public void beforeEachTest() {
        initProperties();
        initConnectionToRoot();
        createTableRecruiter();
        createTableProposal();
    }


    @Test
    public void whenCallGetTimeLastProposalThenReturnTimestampOfLastProposal() throws SQLException {



        addToRecruiterTable();

        final Timestamp sample =
                new Timestamp(new GregorianCalendar(
                        2017, 6, 5, 13, 30, 0).getTimeInMillis());


        addToProposeTable(2017, 6, 5, 11, 30, 0, "propUrl_1", "header_1");
        addToProposeTable(2017, 6, 5, 13, 30, 0, "propUrl_2", "header_2");

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        final Timestamp result = channelToDatabase.getTimeLastProposal();

        channelToDatabase.connectionClose();

        assertThat(result, is(sample));
    }


    @Test
    public void whenProposalIsEmptyThenReturnTrue() {

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        final boolean result = channelToDatabase.firstStart();

        assertTrue(result);
    }

    @Test
    public void whenRecruiterTableNotEmptyThenReturnFalse() {

        addToRecruiterTable();

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        final boolean result = channelToDatabase.firstStart();

        assertFalse(result);
    }

    @Test
    public void whenRecruiterTableContainCartageWithNicknameThenReturnTrue() {

        addToRecruiterTable();

        addToProposeTable(2017, 6, 5, 11, 30, 0, "propUrl_1", "header_1");

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        final boolean result = channelToDatabase.checkExistRecruiterByNickname("author");

        assertTrue(result);
    }

    @Test
    public void whenRecruiterTableNotContainCartageWithNicknameThenReturnFalse() {

        addToRecruiterTable();

        addToProposeTable(2017, 6, 5, 11, 30, 0, "propUrl_1", "header_1");

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        final boolean result = channelToDatabase.checkExistRecruiterByNickname("not exist nickname");

        assertFalse(result);
    }

    @Test
    public void whenIngectionProposalWithNewRecruiterThenRecruiterAddAutomaticAndCheckRecruiterReturnTrue() {

        final Proposal proposal = new Proposal();
        proposal.setNickname("author");
        proposal.setHeader("header");
        proposal.setUrlRecruiter("url_2");
        proposal.setUlrPropose("url_1");
        proposal.setCreate(new Timestamp(System.currentTimeMillis()));


        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        // Test ingection.
        channelToDatabase.ingection(proposal);

        final boolean result = channelToDatabase.checkExistRecruiterByNickname("author");

        assertTrue(result);
    }

    @Test
    public void whenTwoIngectionSuccessfulWithSameNicknameThenRecruiterExistReturnTrue() {

        final Proposal proposal = new Proposal();
        proposal.setNickname("author");
        proposal.setHeader("header");
        proposal.setUrlRecruiter("url");
        proposal.setUlrPropose("url_1");
        proposal.setCreate(new Timestamp(System.currentTimeMillis()));

        final Proposal proposal1 = new Proposal();
        proposal1.setNickname("author");
        proposal1.setHeader("header_1");
        proposal1.setUrlRecruiter("url");
        proposal1.setUlrPropose("url_2");
        proposal1.setCreate(new Timestamp(System.currentTimeMillis()));

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        // Test ingection.
        channelToDatabase.ingection(proposal);
        channelToDatabase.ingection(proposal1);

        final boolean result = channelToDatabase.checkExistRecruiterByNickname("author");

        assertTrue(result);
    }

    @Test
    public void whenWtoProposalIngectionInDatabaseThenDatabaseContainSecondPropose() {

        final Proposal proposal = new Proposal();
        proposal.setNickname("author");
        proposal.setHeader("header");
        proposal.setUrlRecruiter("url");
        proposal.setUlrPropose("url_1");
        proposal.setCreate(new Timestamp(System.currentTimeMillis()));

        final Proposal proposal1 = new Proposal();
        proposal1.setNickname("author");
        proposal1.setHeader("header_1");
        proposal1.setUrlRecruiter("url");
        proposal1.setUlrPropose("url_4");
        proposal1.setCreate(new Timestamp(System.currentTimeMillis()));

        final ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        // Test ingection.
        channelToDatabase.ingection(proposal);
        channelToDatabase.ingection(proposal1);

        final Proposal result = getProposalByHeader("header_1");

        assertThat(result, is(proposal1));
    }

    private Proposal getProposalByHeader(final String header) {

        final Proposal proposal = new Proposal();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT p.nickname, p.url_proposal, p.time_addition, r.url_account" +
                        " FROM proposal AS p LEFT JOIN recruiter AS r ON p.nickname" +
                        " = r.nickname WHERE p.header = (?)")) {

            statement.setString(1, header);

            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            proposal.setNickname(resultSet.getString(1));
            proposal.setUlrPropose(resultSet.getString(2));
            proposal.setCreate(resultSet.getTimestamp(3));
            proposal.setUrlRecruiter(resultSet.getString(4));
            proposal.setHeader(header);

            return proposal;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proposal;
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