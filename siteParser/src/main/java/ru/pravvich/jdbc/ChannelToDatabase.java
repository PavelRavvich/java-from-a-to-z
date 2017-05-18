package ru.pravvich.jdbc;

/**
 * Aggregation all action with DB.
 */
public interface ChannelToDatabase
        extends InjectDataInTables, VerificationStateTables {

    void  connectionClose();

    boolean isClosed();
}
