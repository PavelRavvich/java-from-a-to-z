package ru.pravvich.timing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.jdbc.ChannelToDatabase;
import ru.pravvich.jdbc.DatabaseConnector;
import ru.pravvich.jdbc.JDBCChannel;
import ru.pravvich.parser.*;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ByTheDayControllerTest {

    private Connection connection;
    private PropertiesLoader testProp;
    private PropertiesLoader properties;
    private Timestamp lastTime;

    private void initTimeLimiter() {
        lastTime = new Timestamp(new GregorianCalendar(
                2017, 1, 1, 1, 1, 1).getTimeInMillis());
    }

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

    private void addLastTimeProposal() {
        initTimeLimiter();

        final Proposal proposal = new Proposal();
        proposal.setHeader("header_0000");
        proposal.setNickname("nickname_0000");
        proposal.setUlrPropose("url_0000");
        proposal.setUrlRecruiter("url_0000_r");

        proposal.setCreate(lastTime);

        new JDBCChannel(new DatabaseConnector(new PropertiesLoader("testDatabase"))).ingection(proposal);
    }

    //подготовка тестовой базы
    @Before
    public void beforeEachTest() {
        initProperties();
        initConnectionToRoot();
        createTableRecruiter();
        createTableProposal();
        addLastTimeProposal();
    }

    /**
     * First and second proposes add in database because them create date after compared last in table.
     * Third propose not add in database because him create date before compared last in table.
     */
    @Test
    public void whenByTheDayControllerRunAndPageBreakerDocumentWith3ProposeThen2ProposeAddInDatabase() {

        ChannelToDatabase channelToDatabase = new JDBCChannel(
                new DatabaseConnector(new PropertiesLoader("testDatabase")));

        Queue<Proposal> proposals = new LinkedList<>();
        PageBreaker breaker = new StubPageBreaker(properties);
        ParserSingePage parser = new ParserSingePage(
                proposals, new TimeFormatConverter(), new ProposalValidatorUtil());

        ByTheDayController controller = new ByTheDayController(
                proposals, breaker, parser, channelToDatabase);

        controller.run();

        final int result01 = new Getter().getAmountProposalByHeader("test_header_Java_00");
        final int result02 = new Getter().getAmountProposalByHeader("test_header_Java_01");
        final int result03 = new Getter().getAmountProposalByHeader("test_header_Java 02");

        assertThat(result01, is(1));
        assertThat(result02, is(1));
        assertThat(result03, is(0));
    }

    class Getter {

        private int getAmountProposalByHeader(final String header) {

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 "SELECT COUNT (*) FROM proposal WHERE header = (?)")
            ) {

                statement.setString(1, header);

                final ResultSet resultSet = statement.executeQuery();
                resultSet.next();

                return resultSet.getInt(1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return -1;
        }
    }

    /**
     * Delete all test tables.
     */
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