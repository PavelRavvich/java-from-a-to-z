package ru.pravvich.start;

import ru.pravvich.models.*;

import java.util.Random;

/**
 * Users class
 * @author Pavel Ravvich 01.11.2016
 * @author version 1.0
 * @see #addHeader(String)
 * @see #addOrEditDescription(String, String) +
 * @see #findItemByHeader(String) for work with fields
 * @see #addUsername(String, String) +
 * @see #addCommit(String, String) - Addition commit
 * @see #editionCommit(String, String)
 * @see #deleteCommit(String)
 * @see #findById(int)
 * @see #add(Item) addition new Item
 * @see #findByHeader(String)
 * @see #generateId()
 * @see #getMessage()
 * @see #delete(String) delete Item
 * @see #getPrintArray() array Item for print for user
 * @see #getArrPrintFilter() print with filter revers order
 */
public class Tracker {
    int position = 0;
    public Item[] items = new Item[100];
    private static final Random RN = new Random();
    // for messages
    private String message;

    /**
     * @see TrackerTest#whenStringInThenHeaderInit() test
     */
    public void addHeader(String header) {
        Item item = new Item(header);
        add(item);
    }

    /**
     * Addition item in items array
     * @see TrackerTest#whenObjectTypeItemInThenInArrayItemsInitOneCell() test
     * @param item new item for init in array
     */
    protected void add(Item item) {
        if (!(item == null)) {
            item.setId(generateId());
            this.items[this.position] = item;
            this.position++;
            this.message = "Task / header successfully added";
        } else {
            this.message = "Please header enter.";
        }
    }

    /**
     * a temporary copy Item for the working methods:
     * @see #findItemByHeader(String)
     */
    private Item bufferItem;

    /**
     * @see TrackerTest#thenDescriptionAndHeaderInThenFindItemWithThisHeaderAndAddDescription()
     */
    public void addOrEditDescription(String header, String description) {
        if (description != null) {
            findItemByHeader(header);
            this.bufferItem.setDescription(description);
            this.message = "Description successfully added";
        } else {
            this.message = "You can't enter a blank description.";
        }
    }

    // find need item for add/edition String fields of item. Init bufferItem
    private void findItemByHeader(String header) {
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getHeader().equals(header)) {
                this.bufferItem = this.items[i];
            }
            else {
                this.message = "The task with such a header can't be found.";
            }
        }
    }

    /**
     * @see TrackerTest#thenStringInWhenUsernameInit() test
     */
    public void addUsername(String header, String username) {
        if (username != null) {
            findItemByHeader(header);
            this.bufferItem.setNameUser(username);
            this.message = "You name successfully added";
        } else {
            this.message = "You can't enter a blank Username.";
        }
    }

    /**
     * Addition commit by header
     * @see TrackerTest#whenCommitAddThenCommitAddInLists() test
     * @param header for find needed item
     * @param commit - commit for add
     */
    public void addCommit(String header, String commit) {
        if (commit != null) {
            findItemByHeader(header);
            this.bufferItem.getCommits().add(commit);
            this.message = "Commit successfully added";
        } else {
            this.message = "You can't enter a blank commit.";
        }
    }

    /**
     * Replace oldCommit on newCommit
     * @see TrackerTest#whenNewCommitAndOldCommitInThenMethodFindCommitByOldCommitAndReplaceCommit() test
     * @param oldCommit
     * @param newCommit
     */
    public void editionCommit(String oldCommit, String newCommit) {
        for (int i = 0; i != this.position; i++) {
            for (int j = 0; j != this.items[i].getCommits().size(); j++) {
                String commit = this.items[i].getCommits().get(j);
                if (oldCommit.equals(commit)) {
                    this.items[i].getCommits().remove(j);
                    this.items[i].getCommits().add(j, newCommit);
                    this.message = "«" + oldCommit + "»" + " " + (char) 27 + "[35msuccessfully "
                            + "replace on " + (char)27 + "[0m" + "«" + newCommit + "»";
                }
            }
        }
    }

    /**
     * @see TrackerTest#whenCommitOfStringInThenThisCommitDelete() test
     * @param commit - Commit For Delete
     */
    public void deleteCommit(String commit) {
        for (int i = 0; i != this.position; i++) {
            for (int j = 0; j != this.items[i].getCommits().size(); j++) {
                if (commit.equals(this.items[i].getCommits().get(j))) {
                    this.items[i].getCommits().remove(j);
                    this.message = "Commit successfully delete.";
                }
            }
        }
    }

    /**
     * @see TrackerTest#whenIdInThenItemWithThisIdOut() test
     * @param id - id on which we look for an item
     * @return - found by id item
     */
    protected Item findById(int id) {
        Item result = new Item();
        for (Item item : this.items) {
            if (item != null && item.getId() == id) {
                result = item;
                break;
            } else {
                this.message = "The task with Id does not exist. Please try again.";
            }
        }
        return result;
    }

    /**
     * @see TrackerTest#whenHeaderInThenItemWithThisHeaderOut() test
     * @see TrackerTest#WhenItemWithThisHeaderNotExistThenVariableMassageInit() - if header does not exist
     */
    protected Item findByHeader(String header) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getHeader().equals(header)) {
                result = item;
            } else {
                this.message = "The task with header does not exist. Please try again.";
            }
        }
        return result;
    }

    /**
     * @return unique id
     */
    int generateId() {
        return RN.nextInt() + ((int) System.currentTimeMillis());
    }



    /**
     * @see TrackerTest#whenAddMethodWorkAndInItemEqualsNullThenGetMessageInitMessage() test
     * @return - message for user
     */
    public String getMessage() {
        return this.message;
    }



    /**
     * Delete task (null replacement)
     * @param header - flag for deleted object
     * @see #nullPushInEnd() - using SWAP for new null
     * test:
     * @see TrackerTest#whenMethodWorkThenItemReplacementOnNullAndNullPushInAndArray()
     */
    public void delete(String header) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getHeader().equals(header)) {
                items[i] = null;
                nullPushInEnd();
                this.position--;
                this.message = "Task have been deleted.";
            } else {
                this.message = "The task with header does not exist. Please try again.";
            }
        }
    }



    // Pushes nulls at the end of the array, (after null replacement)
    private void nullPushInEnd() {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] == null) {
                Item temp = this.items[i + 1];
                this.items[i + 1] = null;
                this.items[i] = temp;
            }
        }
    }

    /**
     * @see TrackerTest#whenMethodWorkThenReturnListHeadersAllListsWeHave() test method
     * @return arrPrint list for out to User
     */
    public Item[] getPrintArray() {
        initArrPrint();
        this.message = "We found " + this.arrPrint.length + " tasks";
        return this.arrPrint;
    }

    // Array save all items(without null).
    private Item[] arrPrint = new Item[0];

    // recording values in arrPrint without nulls
    private void initArrPrint() {
        Item[] arrPrint = new Item[this.position];
        for (int i = 0; i != arrPrint.length; i++) {
            arrPrint[i] = this.items[i];
        }
        this.arrPrint = arrPrint;
    }

    /**
     * Filter Reverse Order
     * @see TrackerTest#whenMethodWorkThenReturnListHeadersInReverseOrder()
     * @return Item list for out to User with Reverse Order
     */
    public Item[] getArrPrintFilter() {
        initArrPrint();
        Item[] arrPrintFilter = new Item[this.arrPrint.length];
        for (int i = 0, j = (arrPrintFilter.length - 1);
             (i != arrPrintFilter.length) && (j != -1); i++, j--) {
            arrPrintFilter[i] = this.arrPrint[j];
        }
        this.message = "Reverse order filter. We found " + this.arrPrint.length + " tasks";
        return arrPrintFilter;
    }
}