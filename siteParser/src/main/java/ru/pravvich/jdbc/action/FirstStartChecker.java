package ru.pravvich.jdbc.action;

import ru.pravvich.parser.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Check first time start program.
 */
public class FirstStartChecker extends DatabaseAction {

    /**
     * Default constructor for all child of DatabaseAction.
     *
     * @param properties reader of file.
     * @param connection to database.
     */
    public FirstStartChecker(PropertiesLoader properties, Connection connection) {
        super(properties, connection);
    }

    /**
     * Check first start program by amount cortege in recruiter table.
     * @return true if recruiter table not contain cortege, else false.
     */
    public boolean isFirstStart() {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.getValue("recruiter_is_empty"))

        ) {


            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt(1) == 0;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
