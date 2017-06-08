package ru.pravvich.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Factory for database connection.
 */
public class DBJointHandler implements DBJoint {
    /**
     * Connection to database.
     */
    private Connection connection;
    /**
     * Filename which contains database fileScripts for all transactions.
     */
    private final String fileScripts;
    /**
     * Filename which contains url, fileLogin, passwords to database.
     */
    private final String fileLogin;
    /**
     * Script executor.
     */
    private final ScriptExecutor executor;

    /**
     * Default constructor.
     *
     * @param fileScripts filename which contains database fileScripts for all transactions.
     * @param fileLogin   filename which contains url, fileLogin, passwords to database.
     */
    public DBJointHandler(final String fileScripts, final String fileLogin) {

        this.fileScripts = fileScripts;

        this.fileLogin = fileLogin;


        this.initConnection();


        this.executor = getScriptExecutor();
    }

    /**
     * Get worker for executing transaction with new connection.
     * Each call this is new instance.
     *
     * @return new channel connection.
     * @throws SQLException when fail create connection.
     */
    @Override
    public ScriptExecutor getDBScriptExecutor() throws SQLException {

        return executor;

    }

    private ScriptExecutor getScriptExecutor() {

        return new ScriptExecutor(connection, new PropertiesLoader(fileScripts));

    }

    private void initConnection() {
        try {

            connection = new DatabaseConnection(
                    new PropertiesLoader(fileLogin))
                    .getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close connection to database.
     */
    @Override
    public void closeConnection() {
        try {

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
