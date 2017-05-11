package ru.pravvich.jdbs.requestGeneration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.pravvich.jdbs.DBPropertyLoader;
import ru.pravvich.jdbs.PropertyLoader;

import java.sql.*;

import static org.hamcrest.core.Is.is;

public class TableCreatorTest {

    private static Connection connection;

    private static PropertyLoader property;

    @BeforeClass
    public static void initConnection() {

        property = new DBPropertyLoader();

        try {

            connection = DriverManager.getConnection(
                    property.getRootDB(),
                    property.getUsername(),
                    property.getPassword());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableIsExist(final String tableName) {

        boolean exist = false;

        try (final PreparedStatement statement = connection.prepareStatement(

                "select hasindexes from pg_tables where tablename=(?)")) {


            statement.setString(1, tableName);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                exist = resultSet.getBoolean("hasindexes");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exist;
    }

    @Test
    public void whenTablesNotExistsThenTablesCommentsAndTasksCreates() throws SQLException {

        final TableCreator creator = new TableCreator(connection, property);

        final boolean beforeComments = tableIsExist("comments");
        final boolean beforeTasks = tableIsExist("tasks");


        creator.createTables();


        final boolean afterComments = tableIsExist("comments");
        final boolean afterTasks = tableIsExist("tasks");

        Assert.assertThat(beforeComments, is(false));
        Assert.assertThat(beforeTasks, is(false));

        Assert.assertThat(afterTasks, is(true));
        Assert.assertThat(afterComments, is(true));
    }

    @AfterClass
    public static void clean() {

        try {

            deleteTestsTables();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteTestsTables() throws SQLException {

        connection.prepareStatement("DROP TABLE comments").executeUpdate();
        connection.prepareStatement("DROP TABLE tasks").executeUpdate();

    }
}