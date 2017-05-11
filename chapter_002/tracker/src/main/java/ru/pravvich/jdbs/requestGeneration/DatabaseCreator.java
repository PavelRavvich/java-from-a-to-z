package ru.pravvich.jdbs.requestGeneration;

import ru.pravvich.jdbs.PropertyLoader;
import ru.pravvich.jdbs.requestGeneration.requestBuilder.CreateDatabase;
import ru.pravvich.jdbs.requestGeneration.requestBuilder.DBExists;

import java.sql.*;

public class DatabaseCreator {

    private Connection rootDatabase;

    public void openConnection(final PropertyLoader property) throws SQLException {

        this.rootDatabase = DriverManager.getConnection(
                property.getRootDB(),
                property.getUsername(),
                property.getPassword());

    }

    public void closeConnection() throws SQLException {
        this.rootDatabase.close();
    }

    public boolean existDB(final String nameDtabase) throws SQLException {

        final String request = new DBExists().build(nameDtabase);

        try (final PreparedStatement statement =

                     this.rootDatabase.prepareStatement(request)

        ) {


            final ResultSet resultSet = statement.executeQuery();

            int binaryFlag = 0;


            while (resultSet.next()) {

                binaryFlag = resultSet.getInt("result");

            }

            return (binaryFlag == 1);


        }
    }

    public void createDB(final String nameDatabase) throws SQLException {

        final String script = new CreateDatabase().build(nameDatabase);

        try (final PreparedStatement createDatabase =

                     this.rootDatabase.prepareStatement(script)

        ) {

            // Create database auto_services in root.
            createDatabase.executeUpdate();

        }
    }
}
