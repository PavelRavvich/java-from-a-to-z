package ru.pravvich.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Queue;

/**
 * ParserSingePage for Documents.
 */
public class ParserSingePage {
    /**
     * Converter for time representation in Timestamp.
     */
    private final TimeFormatConverter converter;
    /**
     * Queue save proposals which contains "Java" in header.
     */
    private final Queue<Proposal> proposals;
    /**
     * Check proposal for Java type.
     */
    private final ProposalValidatorUtil validator;

    /**
     * Default constructor.
     *
     * @param proposals queue for save valid proposals.
     * @param converter time format to Timestamp.
     * @param validator check proposal for Java type.
     */
    public ParserSingePage(final Queue<Proposal> proposals,
                           final TimeFormatConverter converter,
                           final ProposalValidatorUtil validator) {

        this.converter = converter;
        this.proposals = proposals;
        this.validator = validator;
    }

    /**
     * Extracts Proposal objects from Document and valid Proposal add in Queue.
     *
     * @param document for parsing.
     */
    public void parseDocument(final Document document) {

        Proposal proposal = new Proposal();

        final Elements altCol = document.select(".altCol");
        final Elements posts = document.select(".postslisttopic > a");

        for (int i = 0; i < altCol.size(); i++) {

            final Element post = posts.get(i);
            final Element element = altCol.get(i);
            final Elements innerElements = element.children();


            if (!innerElements.isEmpty()) {

                //Header of proposal.
                proposal.setHeader(post.text());

                //Url to proposal page.
                proposal.setUlrPropose(post.attr("href"));

                //Recruiter account url.
                proposal.setUrlRecruiter(innerElements.get(0).attr("href"));

                //Recruiter name.
                proposal.setNickname(innerElements.get(0).text());

            } else {

                proposal.setCreate(this.converter.convert(element.text()));

                addInQueue(proposal);

                proposal = new Proposal();

            }
        }
    }

    /**
     * Add proposal in proposals queue if proposal valid.
     *
     * @param proposal for check and may be add.
     */
    private void addInQueue(Proposal proposal) {
        if (validator.isValid(proposal))
            proposals.add(proposal);
    }
}
