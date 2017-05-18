package ru.pravvich.timing;

import ru.pravvich.jdbc.ChannelToDatabase;
import ru.pravvich.parser.PageBreaker;
import ru.pravvich.parser.ParserSingePage;
import ru.pravvich.parser.Proposal;

import java.sql.Timestamp;
import java.util.Queue;

/**
 * Determines first start.
 */
public class CollectorDataForFirstStart extends ByTheDayController {
    /**
     * Default constructor.
     *
     * @param proposals   contain actual by header for addition in database proposals.
     * @param pageBreaker generate url of next forum page.
     * @param parser      pars html to Proposal object.
     * @param channelToDatabase   interface for database actions.
     */
    public CollectorDataForFirstStart(Queue<Proposal> proposals, PageBreaker pageBreaker, ParserSingePage parser, ChannelToDatabase channelToDatabase) {
        super(proposals, pageBreaker, parser, channelToDatabase);
    }

    /**
     * @return current time minus 1 year.
     */
    @Override
    protected Timestamp initTimeBound() {
        return new Timestamp(System.currentTimeMillis() - 31_536_000_000L);
    }
}
