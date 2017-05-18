package ru.pravvich.jdbc;

import ru.pravvich.jdbc.action.*;
import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.Timestamp;

/**
 * All actions with DB.
 */
public class JDBCChannel implements ChannelToDatabase {
    /**
     * Connection to database.
     */
    private final DatabaseConnector connection;
    /**
     * Properties file reader.
     */
    private final PropertiesLoader properties;

    /**
     * Default constructor.
     *
     * @param connection to database.
     */
    public JDBCChannel(final DatabaseConnector connection) {
        this.properties = new PropertiesLoader("DBAction");
        this.connection = connection;
    }

    /**
     * Check empty proposals table or not.
     *
     * @return true if database is empty, else false.
     */
    @Override
    public boolean firstStart() {

        return new FirstStartChecker(properties, connection.getConnection()).isFirstStart();

    }

    /**
     * Find last date in proposals table.
     *
     * @return date of last write. If database is empty return new Timestamp(0).
     */
    @Override
    public Timestamp getTimeLastProposal() {

        return new LastPropose(properties, connection.getConnection()).getTimeLastPropose();

    }

    /**
     * Add proposal in database.
     *
     * @param proposal for add.
     */
    @Override
    public void ingection(final Proposal proposal) {

        if (checkExistRecruiterByNickname(proposal.getNickname())) {

            new InjectorInProposal(properties, connection.getConnection()).injectInProposal(proposal);

        } else {

            new InjectorInRecruiter(properties, connection.getConnection()).injectInRecruiter(proposal);

            new InjectorInProposal(properties, connection.getConnection()).injectInProposal(proposal);
        }
    }

    /**
     * Check exist recruiter in recruiter table by name.
     * @param nickname for check.
     * @return true if table recruiter contains nickname, else false.
     */
    @Override
    public boolean checkExistRecruiterByNickname(final String nickname) {

        return new RecruiterExistChecker(properties, connection.getConnection()).recruiterIsExist(nickname);

    }

    @Override
    public void connectionClose() {
        connection.closeDatabaseConnection();
    }

    @Override
    public boolean isClosed() {
        return connection.isClose();
    }
}
