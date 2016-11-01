package ru.pravvich.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import ru.pravvich.models.Item;

public class TrackerTest {

    /**
     * @see Tracker#addCommit(String, String)
     */
    @Test
    public void whenCommitAddThenCommitAddInLists() {
        Tracker tracker = new Tracker();
        // add first obj
        Item item = new Item();
        item.header = "0";
        tracker.add(item);
        // add second obj
        Item itemSecond = new Item();
        itemSecond.header = "1";
        tracker.add(itemSecond);
        //use method for first item
        tracker.addCommit("commit - 0", "0");
        tracker.addCommit("commit - 1", "0");
        //use method for second item
        tracker.addCommit("commit(1) - 0", "1");
        String resultForSecondItem = tracker.items[1].getCommits().get(0);
        //check
        String resultFirstCommitForFirstItem = tracker.items[0].getCommits().get(0);
        String resultSecondCommitForFirstItem = tracker.items[0].getCommits().get(1);
        assertThat(resultFirstCommitForFirstItem, is("commit - 0"));
        assertThat(resultSecondCommitForFirstItem, is("commit - 1"));
        assertThat(resultForSecondItem, is("commit(1) - 0"));
    }

    /**
     * @see Tracker#editionCommit(String, String) method
     */
    @Test
    public void whenNewCommitAndOldCommitInThenMethodFindCommitByOldCommitAndReplaceCommit() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.header = "0";
        tracker.add(item);
        tracker.addCommit("commit - 0", "0");
        tracker.addCommit("commit - 1", "0");
        //use method
        tracker.editionCommit("commit - 0", "Replace");
        String result = tracker.items[0].getCommits().get(0);
        assertThat(result, is("Replace"));
    }

    /**
     * @see Tracker#deleteCommit(String) method
     */
    @Test
    public void whenCommitOfStringInThenThisCommitDelete() {
        Tracker tracker = new Tracker();
        // add first obj
        Item item = new Item();
        item.header = "0";
        tracker.add(item);
        // add second obj
        Item itemSecond = new Item();
        itemSecond.header = "1";
        tracker.add(itemSecond);
        //use method for first item
        tracker.addCommit("commit - 0", "0");
        tracker.addCommit("commit - 1", "0");
        //check size commits list
        tracker.deleteCommit("commit - 0");
        int result = tracker.items[0].getCommits().size();
        assertThat(result, is(1));
    }

    /**
     * @see Tracker#findById(int) method
     */
    @Test
    public void whenIdInThenItemWithThisIdOut() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.description = "description";
        item.header = "header";
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
        item.description = "description";
        item.header = "header";
        tracker.add(item);
        String header = "header";
        Item result = tracker.findByHeader(header);
        assertThat(result, is(item));
    }

    /**
     * @see Tracker#findByHeader(String)
     * @see Tracker#initMessageNothingFound()
     */
    @Test
    public void WhenThen() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.description = "description";
        item.header = "header";
        tracker.add(item);
        String header = "head";
        tracker.findByHeader(header);
        String result = tracker.getMessage();
        assertThat(result, is("Nothing found./nPlease try again."));
    }

    /**
     * @see Tracker#add(Item) test method
     */
    @Test
    public void whenObjectTypeItemInThenInArrayItemsInitOneCell() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        item.description = "description";
        item.header = "header";
        tracker.add(item);
        assertThat(tracker.items[0], is(item));
    }

    /**
     * @see Tracker#getMessage() test method
     */
    @Test
    public void whenAddMethodWorkAndInItemEqualsNullThenGetMessageInitMessage() {
        Tracker tracker = new Tracker();
        Item item = null;
        tracker.add(item);
        String result = tracker.getMessage();
        String check = "Please header enter.";
        assertThat(result, is(check));
    }

    /**
     * @see Tracker#edition(String, String) method
     */
    @Test
    public void whenHeaderAndDescriptionInThenOldDescriptionReplacement() {
        Tracker tracker = new Tracker();
        // init and add Item
        Item item = new Item();
        item.description = "description";
        item.header = "header";
        tracker.add(item);
        // test method
        tracker.edition("header", "This is new description");
        String result = tracker.items[0].description;
        assertThat(result, is("This is new description"));
    }

    /**
     * @see Tracker#edition(String, String) method(if new description == null)
     */
    @Test
    public void whenDescriptionForReplacementEqualsNullThenInitMessage() {
        Tracker tracker = new Tracker();
        // init and add Item
        Item item = new Item();
        item.description = "description";
        item.header = "header";
        tracker.add(item);
        // test method
        tracker.edition("header", null);
        String result = tracker.getMessage();
        String check = "New description enter require.";
        assertThat(result, is(check));
    }

    /**
     * @see Tracker#delete(String) checking method
     */
    @Test
    public void whenMethodWorkThenItemReplacementOnNullAndNullPushInAndArray() {
        Tracker tracker = new Tracker();
        // init and add first Item
        Item itemFirst = new Item();
        itemFirst.description = "description";
        itemFirst.header = "header";
        tracker.add(itemFirst);
        // init and add second Item
        Item itemSecond = new Item();
        itemSecond.description = "description two";
        itemSecond.header = "header two";
        tracker.add(itemSecond);
        //check method
        tracker.delete("header");
        // init result
        Item result = tracker.items[0];
        assertThat(result, is(itemSecond));
        // test message about delete
        String messageResult = tracker.getMessage();
        String check = "Task have been deleted.";
        assertThat(messageResult, is(check));
    }

    /**
     * @see Tracker#getPrintArray()
     */
    @Test
    public void whenMethodWorkThenReturnListHeadersAllListsWeHave() {
        Tracker tracker = new Tracker();
        // init and add first Item
        Item itemFirst = new Item();
        itemFirst.description = "description";
        itemFirst.header = "header";
        tracker.add(itemFirst);
        // init and add second Item
        Item itemSecond = new Item();
        itemSecond.description = "description two";
        itemSecond.header = "header two";
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
        itemFirst.description = "description";
        itemFirst.header = "header";
        tracker.add(itemFirst);
        // init and add second Item
        Item itemSecond = new Item();
        itemSecond.description = "description two";
        itemSecond.header = "header two";
        tracker.add(itemSecond);
        //check method
        Item[] result = tracker.getArrPrintFilter();
        Item[] check = {itemSecond, itemFirst};
        assertThat(result, is(check));
    }
}
