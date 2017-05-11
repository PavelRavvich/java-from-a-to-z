package ru.pravvich.jdbs.requestGeneration;

import ru.pravvich.jdbs.PropertyLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreator {

    private final Connection connection;
    private final PropertyLoader property;

    public TableCreator(final Connection connection,

                        final PropertyLoader property) {


        this.connection = connection;

        this.property = property;

    }


    public void createTables() throws SQLException {

        try (final PreparedStatement comments =

                     this.connection.prepareStatement(

                             this.property.getScriptForCreateComments());


             final PreparedStatement tasks =

                     this.connection.prepareStatement(

                             this.property.getScriptForCreateTasks())

        ) {


            tasks.executeUpdate();

            comments.executeUpdate();


        }
    }
}
