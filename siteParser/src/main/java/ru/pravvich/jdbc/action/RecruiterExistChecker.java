package ru.pravvich.jdbc.action;

import ru.pravvich.parser.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get info about exist recruiter.
 */
public class RecruiterExistChecker extends DatabaseAction {

    /**
     * @see DatabaseAction#DatabaseAction(PropertiesLoader, Connection)
     */
    public RecruiterExistChecker(final PropertiesLoader properties,
                                 final Connection connection) {

        super(properties, connection);
    }

    /**
     * Check exist proposal's nickname in table recruiter.
     * @param nickname for check.
     * @return true in exist, else false.
     */
    public boolean recruiterIsExist(final String nickname) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.getValue("recruiter_is_exist"))
        ) {


            statement.setString(1, nickname);

            final ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            return resultSet.getInt(1) != 0;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
