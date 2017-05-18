package ru.pravvich.jdbc;

import ru.pravvich.parser.Proposal;

/**
 * Add proposal in database..
 */
interface InjectDataInTables {
    /**
     * Add proposal in database.
     *
     * @param proposal for add.
     */
    void ingection(final Proposal proposal);
}
