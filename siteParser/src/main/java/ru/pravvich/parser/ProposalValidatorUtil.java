package ru.pravvich.parser;

/**
 * Validator for proposal by header content.
 */
public class ProposalValidatorUtil {

    /**
     * @param proposal for check.
     * @return true if Java's header, else false.
     */
    public boolean isValid(final Proposal proposal) {

        final String hed = proposal.getHeader().toLowerCase();

        return hed.contains("java") &&
                !hed.contains("java script") &&
                !hed.contains("java_script") &&
                !hed.contains("javascript");
    }

}
