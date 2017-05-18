package ru.pravvich.jdbc.action;

import ru.pravvich.parser.PropertiesLoader;
import ru.pravvich.parser.Proposal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Ingection in proposal table.
 */
public class InjectorInProposal extends DatabaseAction {

    /**
     * @see DatabaseAction#DatabaseAction(PropertiesLoader, Connection)
     */
    public InjectorInProposal(final PropertiesLoader properties,
                              final Connection connection) {

        super(properties, connection);
    }

    /**
     * Injection Proposal object in proposal table.
     *
     * @param proposal for addition.
     */
    public void injectInProposal(final Proposal proposal) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             properties.getValue("add_to_proposal"))
        ) {


            statement.setString(1, proposal.getHeader());

            statement.setString(2, proposal.getUlrPropose());

            statement.setTimestamp(3, proposal.getCreateTime());

            statement.setString(4, proposal.getNickname());


            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
