package ru.pravvich;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import ru.pravvich.jdbc.ChannelToDatabase;
import ru.pravvich.jdbc.DatabaseConnector;
import ru.pravvich.jdbc.JDBCChannel;
import ru.pravvich.parser.*;
import ru.pravvich.timing.ByTheDayController;
import ru.pravvich.timing.CollectorDataForFirstStart;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

/**
 * Initialization state for start app.
 */
class InitializerStartStatement {
    /**
     * Channel to database with automatic himself support.
     */
    private ChannelToDatabase channelDB;
    /**
     * Queue which temp cash before addition data in database.
     */
    private Queue<Proposal> proposals;
    /**
     * Parser html pages to Proposal object.
     */
    private ParserSingePage parser;
    /**
     * Break page, generate next url page of website.
     */
    private PageBreaker breaker;
    /**
     * For cycle repeat collecting data.
     */
    private Timer timer;

    /**
     * Default constructor.
     */
    InitializerStartStatement() {

        initConfigForLog4j();

        proposals = new LinkedList<>();

        timer = new Timer();

        initParser();

        initChannelToDB();

        initPageBreaker();
    }

    /**
     * Start app.
     *
     * @param period between collection data.
     */
    void start(long period) {

        if (channelDB.firstStart()) {


            timer.schedule(new CollectorDataForFirstStart(proposals, breaker, parser, channelDB), 0);

            startCycleCollectData(86_400_000L, period);

        } else {

            startCycleCollectData(0, period);

        }

    }

    /**
     * Initialization rules for logger.
     */
    private void initConfigForLog4j() {

        BasicConfigurator.configure(
                new ConsoleAppender(
                        new PatternLayout(
                                new PropertiesLoader("log4j").getValue(
                                        "log4j.appender.stdout.layout.conversionPattern"
                                )
                        )
                )
        );

    }

    /**
     * Cycle collecting data.
     *
     * @param delay  before next collecting data in milliseconds.
     * @param period between collection data in milliseconds.
     */
    private void startCycleCollectData(long delay, long period) {
        timer.schedule(
                new ByTheDayController(proposals, breaker, parser, channelDB),
                delay, period);
    }

    private void initChannelToDB() {
        channelDB = new JDBCChannel(
                new DatabaseConnector(
                        new PropertiesLoader(
                                "DBAction")
                )
        );
    }

    private void initParser() {
        parser = new ParserSingePage(
                proposals,
                new TimeFormatConverter(),
                new ProposalValidatorUtil()
        );
    }

    private void initPageBreaker() {
        breaker = new PageBreaker(
                new PropertiesLoader("siteURL")
        );
    }
}
