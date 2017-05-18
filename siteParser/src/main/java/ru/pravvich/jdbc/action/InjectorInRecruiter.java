package ru.pravvich.jdbc.action;

import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Ingection in recruiter table.
 */
public class InjectorInRecruiter extends DatabaseAction {

    /**
     * @see DatabaseAction#DatabaseAction(PropertiesLoader, Connection)
     */
    public InjectorInRecruiter(final PropertiesLoader properties,
                               final Connection connection) {
        super(properties, connection);
    }

    /**
     * Injection Proposal object in recruiter table.
     *
     * @param proposal which contain recruiter for addition .
     */
    public void injectInRecruiter(final Proposal proposal) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.getValue("add_to_recruiter"))
        ) {


            statement.setString(1, proposal.getNickname());

            statement.setString(2, proposal.getUrlRecruiter());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
