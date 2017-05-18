package ru.pravvich.jdbc;

import java.sql.Timestamp;

/**
 * Determines methods for check state tables.
 */
public interface VerificationStateTables {
    /**
     * Check empty proposals table or not.
     *
     * @return true if database is empty, else false.
     */
    boolean firstStart();

    /**
     * Find last date in proposals table.
     *
     * @return date of last write. If database is empty return new Timestamp(0).
     */
    Timestamp getTimeLastProposal();

    /**
     * Check exist recruiter in recruiter table by name.
     * @param nickname for check.
     * @return true if table recruiter contains nickname, else false.
     */
    boolean checkExistRecruiterByNickname(final String nickname);
}
