package ru.pravvich.timing;

import org.apache.log4j.Logger;
import ru.pravvich.jdbc.ChannelToDatabase;
import ru.pravvich.parser.PageBreaker;
import ru.pravvich.parser.ParserSingePage;
import ru.pravvich.parser.Proposal;

import java.sql.Timestamp;
import java.util.Queue;
import java.util.TimerTask;

/**
 * By day run collection data from website.
 */
public class ByTheDayController extends TimerTask {
    /**
     * Logger for valid Proposes.
     */
    private static final Logger log = Logger.getLogger(ByTheDayController.class);
    /**
     * Contain actual Proposal is filled in at the time call parser.parseDocument(pageBreaker.next()).
     */
    private final Queue<Proposal> proposals;
    /**
     * Break pages. Generate url of next forum page.
     */
    private final PageBreaker pageBreaker;
    /**
     * Pars html to Proposal object.
     */
    private final ParserSingePage parser;
    /**
     * Interface for database actions.
     */
    private final ChannelToDatabase channelToDatabase;
    /**
     * Availability indicator of fresh Proposals possible.
     */
    private boolean freshProposalsPossible;

    /**
     * Default constructor.
     *
     * @param proposals contain actual by header for addition in database proposals.
     * @param pageBreaker generate url of next forum page.
     * @param parser pars html to Proposal object.
     * @param channelToDatabase interface for database actions.
     */
    public ByTheDayController(final Queue<Proposal> proposals,
                              final PageBreaker pageBreaker,
                              final ParserSingePage parser,
                              final ChannelToDatabase channelToDatabase) {

        this.pageBreaker = pageBreaker;
        this.proposals = proposals;
        this.channelToDatabase = channelToDatabase;
        this.parser = parser;

    }

    @Override
    public void run() {

        proposals.clear();

        startPeriodicCollectionData();
    }

    /**
     * Determines the boundary after which proposals not addition in database.
     *
     * @return time line.
     */
    protected Timestamp initTimeBound() {
        return channelToDatabase.getTimeLastProposal();
    }

    /**
     * Determines collection data each day.
     */
    private void startPeriodicCollectionData() {

        freshProposalsPossible = true;

        pageBreaker.resetPageCounter();


        final Timestamp lastProposal = initTimeBound();


        while (freshProposalsPossible) {

            parser.parseDocument(pageBreaker.next());

            // Write in queue proposals content of one page.
            addNextPageProposesInDatabase(lastProposal);


        }
    }

    /**
     * Addition in database all valid proposes.
     *
     * @param lastProposal datetime last propose which contains in database.
     */
    private void addNextPageProposesInDatabase(final Timestamp lastProposal) {

        // Save last proposal from queue on each iteration.
        Timestamp temp = null;

        while (!proposals.isEmpty() &&

                lastProposal.before(temp = proposals.peek().getCreateTime())

                ) {


            log.info(proposals.peek());

            channelToDatabase.ingection(proposals.poll());

        }

        // Last parsed page did not contain valid propose.
        if (temp == null) return;

        // We collected all new proposes.
        if (!lastProposal.before(temp)) freshProposalsPossible = false;
    }
}
