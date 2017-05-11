package ru.pravvich.start;

import ru.pravvich.jdbs.ConnectionReactor;
import ru.pravvich.jdbs.PropertyLoader;
import ru.pravvich.jdbs.requestGeneration.DatabaseCreator;
import ru.pravvich.jdbs.requestGeneration.Generator;
import ru.pravvich.jdbs.requestGeneration.RequestGenerator;
import ru.pravvich.jdbs.requestGeneration.TableCreator;
import ru.pravvich.jdbs.sqlAdapters.*;
import ru.pravvich.models.Item;

import java.sql.SQLException;

/**
 * Tracker with database.
 */
public class SQLTracker extends Tracker {
    /**
     * Generator PostgresSQL requests.
     */
    private final Generator requests;
    /**
     * Connection controller.
     */
    private final ConnectionReactor reactor;
    /**
     * Connection controller.
     */
    private final PropertyLoader property;

    /**
     * Default constructor.
     */
    public SQLTracker(final PropertyLoader property) {
        this.property = property;
        this.reactor = new ConnectionReactor(property);
        this.requests = new RequestGenerator();

        this.checkDatabase();
    }

    /**
     * Close connection of current SQLTracker instance.
     */
    public void closeConnection() {
        try {

            this.reactor.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check database exist.
     * If database exist to do nothing.
     * If database not exist create database with nameDatabase and create tables.
     */
    private void checkDatabase() {

        final String nameDatabase = "auto_services";

        final DatabaseCreator databaseCreator = new DatabaseCreator();

        try {
            databaseCreator.openConnection(this.property);

            if (databaseCreator.existDB(nameDatabase)) {

                databaseCreator.closeConnection();
                return;

            } else {

                databaseCreator.createDB(nameDatabase);

                databaseCreator.closeConnection();

            }


            new TableCreator(this.reactor.get(), this.property).createTables();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add description in exist task(Item).
     *
     * @param id          of task for update.
     * @param description for addition.
     */
    @Override
    public void addDescription(final int id, final String description) {
        try {

            new AdditionDescription(this.reactor, this.requests)
                    .addDescription(id, description);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replace header by id Item.
     *
     * @param item item for replace.
     */
    @Override
    public void updateItem(final Item item) {
        try {

            new UpdateItem(this.reactor, this.requests).updateItem(item);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete task with all comments.
     *
     * @param item for delete
     */
    @Override
    public void delete(final Item item) {
        try {

            new DeleteItem(this.reactor, this.requests).delete(item);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add item in table 'tasks'.
     *
     * @param item for adding.
     */
    @Override
    public void add(final Item item) {
        try {

            new AdditionItem(this.reactor, this.requests).addItem(item);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find Item by header.
     *
     * @param header which have find Item.
     * @return Item which contain header.
     */
    @Override
    public Item findByHeader(final String header) {

        final Item empty = new Item();

        try {

            return new FindByHeader(this.reactor, this.requests).findByHeader(header);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empty;
    }

    /**
     * Addition comment to task.
     *
     * @param item   for comment.
     * @param commit commit for add
     */
    @Override
    public void addCommit(final Item item, final String commit) {
        try {

            new AdditionComment(this.reactor, this.requests).addCommit(item, commit);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edition commit by content old commit.
     *
     * @param oldCommit for replace.
     * @param newCommit for replace.
     */
    @Override
    public void editionCommit(final String oldCommit, final String newCommit) {
        try {

            new EditionComment(this.reactor, this.requests).editionCommit(oldCommit, newCommit);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleted comment.
     *
     * @param commit value for delete.
     */
    @Override
    public void deleteCommit(final String commit) {
        try {

            new DeleteComment(this.reactor, this.requests).deleteCommit(commit);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find item by id in table tasks.
     *
     * @param id for search.
     * @return Item with state from database. Include comments.
     */
    @Override
    public Item findById(final int id) {

        final Item empty = new Item();

        try {

            return new FindById(this.reactor, this.requests).findById(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empty;
    }
}
