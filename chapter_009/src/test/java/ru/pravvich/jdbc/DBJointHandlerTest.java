package ru.pravvich.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by pavel on 02.06.17.
 */
public class DBJointHandlerTest {

    @Test
    public void whenCreateDBJoinInstanceThenResultOfMethodGetDBExecutorNotNull() throws SQLException {
        final DBJoint db = new DBJointHandler("database_scripts", "authentication_database");
        final ScriptExecutor dbExecutor = db.getDBExecutor();

        Assert.assertNotNull(dbExecutor);
        db.closeConnection();
    }


}