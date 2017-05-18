package ru.pravvich.parser;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProposalValidatorUtilTest {

    @Test
    public void whenProposalHaveHeaderWithJavaThenIsValidReturnTrue() {

        final Proposal proposal = new Proposal();
        proposal.setHeader("Java");

        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        final boolean result = validator.isValid(proposal);
        assertTrue(result);
    }

    @Test
    public void whenProposalHaveHeaderWithJavaAndSomethingMoreThenIsValidReturnTrue() {

        final Proposal proposal = new Proposal();
        proposal.setHeader("something Java something");

        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        final boolean result = validator.isValid(proposal);
        assertTrue(result);
    }

    @Test
    public void whenProposalHaveHeaderWithJavaScriptThenIsValidReturnFalse() {

        final Proposal proposal = new Proposal();
        proposal.setHeader("something Java Script something");

        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        final boolean result = validator.isValid(proposal);
        assertFalse(result);
    }

    @Test
    public void whenProposalHaveHeaderWithJavaScriptInOneWordThenIsValidReturnFalse() {

        final Proposal proposal = new Proposal();
        proposal.setHeader("something JavaScript something");

        final ProposalValidatorUtil validator = new ProposalValidatorUtil();

        final boolean result = validator.isValid(proposal);
        assertFalse(result);
    }
}