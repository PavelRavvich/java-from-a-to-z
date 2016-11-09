package ru.pravvich.start;

import org.junit.Test;
import ru.pravvich.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrackerTest {

    /**
     * @see Tracker#addDescription(int, String)
     */
    @Test
    public void whenIdAndDescriptionInThenItemAddDescription() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("header");
        tracker.add(item);
        int  id = tracker.getItems()[0].getId();
        tracker.addDescription(id, "desc");
        String result = tracker.items[0].getDescription();
        assertThat(result, is("desc"));
    }


    /**
     * @see Tracker#updateItem(Item) method
     */
    @Test
    public void whenItemInThenItemUpdate() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("header");
        Item item1 = new Item();
        item1.setHeader("header");
        tracker.add(item);
        tracker.add(item1);
        Item result = new Item();
        result.setHeader("header");
        result.setId(item.getId());
        tracker.updateItem(result);
        assertThat(result, is(tracker.getItems()[0]));
    }

    /**
     * @see Tracker#addCommit(Item, String)
     */
    @Test
    public void whenCommitAddThenCommitAddInLists() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("header");
        tracker.add(item);
        tracker.addCommit(item, "commit");
        String result = item.getCommits().get(0);
        assertThat(result, is("commit"));
    }

    /**
     * @see Tracker#editionCommit(String, String) method
     */
    @Test
    public void whenNewCommitAndOldCommitInThenMethodFindCommitByOldCommitAndReplaceCommit() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("header");
        tracker.add(item);
        tracker.addCommit(item,"commit_01");
        tracker.addCommit(item, "commit_02");
        //use method
        tracker.editionCommit("commit_02", "update commit");
        String result = tracker.getItems()[0].getCommits().get(1);
        assertThat(result, is("update commit"));
    }

    /**
     * @see Tracker#deleteCommit(String) method
     */
    @Test
    public void whenCommitOfStringInThenThisCommitDelete() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setHeader("header");
        tracker.add(item);
        tracker.addCommit(item,"commit_01");
        tracker.addCommit(item, "commit_02");
        //check size commits list
        tracker.deleteCommit("commit_01");
        int result = tracker.getItems()[0].getCommits().size();
        assertThat(result, is(1));
    }

    /**
     * @see Tracker#findById(int) method
     */
    @Test
    public void whenIdInThenItemWithThisIdOut() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setDescription("description");
        item.setHeader("header");
        tracker.add(item);
        int id = item.getId();
        Item result = tracker.findById(id);
        assertThat(result, is(item));
    }

    /**
     * @see Tracker#findByHeader(String)
     */
    @Test
    public void whenHeaderInThenItemWithThisHeaderOut() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setDescription("description");
        item.setHeader("header");
        tracker.add(item);
        String header = "header";
        Item result = tracker.findByHeader(header);
        assertThat(result, is(item));
    }

    /**
     * @see Tracker#findByHeader(String)
     */
    @Test
    public void whenItemWithThisHeaderNotExistThenVariableMassageInit() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setDescription("description");
        item.setHeader("header");
        tracker.add(item);
        String header = "head";
        tracker.findByHeader(header);
        String result = tracker.getMessage();
        assertThat(result, is("The task does not exist. Please try again."));
    }

    /**
     * @see Tracker#add(Item) test method
     */
    @Test
    public void whenObjectTypeItemInThenInArrayItemsInitOneCell() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.setDescription("description");
        item.setHeader("header");
        tracker.add(item);
        assertThat(tracker.getItems()[0], is(item));
    }

    /**
     * @see Tracker#delete(Item) checking method
     */
    @Test
    public void whenMethodWorkThenItemReplacementOnNullAndNullPushInAndArray() {
        Tracker tracker = new Tracker();
        // init and add first Item
        Item item = new Item();
        item.setHeader("header");
        tracker.add(item);
        tracker.delete(item);
        assertThat(tracker.getPosition(), is(0));
    }

    /**
     * @see Tracker#getPrintArray()
     */
    @Test
    public void whenMethodWorkThenReturnListHeadersAllListsWeHave() {
        Tracker tracker = new Tracker();
        // init and add first Item
        Item itemFirst = new Item();
        itemFirst.setDescription("description");
        itemFirst.setHeader("header");
        tracker.add(itemFirst);
        // init and add second Item
        Item itemSecond = new Item();
        itemSecond.setDescription("description two");
        itemSecond.setHeader("header two");
        tracker.add(itemSecond);
        //check method
        Item[] result = tracker.getPrintArray();
        Item[] check = {itemFirst, itemSecond};
        assertThat(result, is(check));
    }

    /**
     * @see Tracker#getArrPrintFilter()
     */
    @Test
    public void whenMethodWorkThenReturnListHeadersInReverseOrder() {
        Tracker tracker = new Tracker();
        // init and add first Item
        Item itemFirst = new Item();
        itemFirst.setDescription("description");
        itemFirst.setHeader("header");
        tracker.add(itemFirst);
        // init and add second Item
        Item itemSecond = new Item();
        itemSecond.setDescription("description two");
        itemSecond.setHeader("header two");
        tracker.add(itemSecond);
        //check method
        Item[] result = tracker.getArrPrintFilter();
        Item[] check = {itemSecond, itemFirst};
        assertThat(result, is(check));
    }
}
