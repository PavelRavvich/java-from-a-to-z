package ru.pravvich.start;

import ru.pravvich.models.*;

import java.util.Random;

/**
 * Users class
 * @author Pavel Ravvich 01.11.2016
 * @author version 1.0
 * @see #addHeader(String)
 * @see #addDescription(String, String)
 * @see #addUsername(String, String)
 * @see #addCommit(String, String) - Addition commit
 * @see #editionCommit(String, String)
 * @see #deleteCommit(String)
 * @see #findById(int)
 * @see #add(Item) addition new Item
 * @see #findByHeader(String)
 * @see #generateId()
 * @see #getMessage()
 * @see #edition(String, String) edition description
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
    public void add(Item item) {
        if (!(item == null)) {
            item.setId(generateId());
            this.items[this.position] = item;
            this.position++;
        } else {
            initMessageAboutHeader();
        }
    }

    /**
     * @see TrackerTest#thenDescriptionAndHeaderInThenFindItemWithThisHeaderAndAddDescription()
     */
    public void addDescription(String header, String description) {
        if (description != null) {
            for (int i = 0; i != this.position; i++) {
                if (this.items[i].getHeader().equals(header)) {
                    this.items[i].setDescription(description);
                }
            }
        } else {
            this.message = "Enter description";
        }
    }

    /**
     * @see TrackerTest#thenStringInWhenUsernameInit() test
     */
    public void addUsername(String header, String username) {
        if (username != null) {
            for (int i = 0; i != this.position; i++) {
                if (this.items[i].getHeader().equals(header)) {
                    this.items[i].setNameUser(username);
                }
            }
        } else {
            this.message = "Enter you name";
        }
    }

    /**
     * Addition commit by header
     * @see TrackerTest#whenCommitAddThenCommitAddInLists() test
     * @param commit - commit for add
     * @param header for find needed item
     */
    public void addCommit(String commit, String header) {
        for (int i = 0; i != this.position; i++) {
            String findHeader = this.items[i].getHeader();
            if (findHeader.equals(header)) {
                this.items[i].getCommits().add(commit);
                break;
            }
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
                initMessageNothingFound();
            }
        }
        return result;
    }

    /**
     * @see TrackerTest#whenHeaderInThenItemWithThisHeaderOut() test
     */
    protected Item findByHeader(String header) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getHeader().equals(header)) {
                result = item;
            } else {
                initMessageNothingFound();
            }
        }
        return result;
    }

    /**
     * message if Nothing found. Use:
     * @see #findByHeader(String)
     * @see #findById(int)
     */
    private void initMessageNothingFound() {
        this.message = "Nothing found./nPlease try again.";
    }



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

    // init message for User if header == null
    private void initMessageAboutHeader() {
        this.message = "Please header enter.";

    }

    /**
     * Use header for find need object, and replacement her description field.
     * @see TrackerTest#whenHeaderAndDescriptionInThenOldDescriptionReplacement test
     * @param header - for finder task
     * @param newDesc - new description for this task
     * If description for replacement == null then:
     * @see #initMessageAboutDescription() use
     * @see TrackerTest#whenDescriptionForReplacementEqualsNullThenInitMessage() test
     */
    public void edition(String header, String newDesc) {
        for (int i = 0; i < this.position; i++) {
            String getHeader = this.items[i].getHeader();
            if (getHeader.equals(header)) {
                setNewDescription(newDesc);
                this.items[i].description = this.newDescription;
            }
        }
    }

    // save new description
    private String newDescription;

    // setter for newDescription (with check isn't null)
    private void setNewDescription(String newDesc) {
        if (newDesc == null) {
            initMessageAboutDescription();
        } else {
            this.newDescription = newDesc;
        }
    }


    // init message for User if description == null
    private void initMessageAboutDescription() {
        this.message = "New description enter require.";
    }

    /**
     * Delete task (null replacement)
     * @param header - flag for deleted object
     * @see #nullPushInEnd() - using SWAP for new null
     * test:
     * @see TrackerTest#whenMethodWorkThenItemReplacementOnNullAndNullPushInAndArray()
     */
    public void delete(String header) {
        for (int i = 0; i < position; i++) {
            if (this.items[i].header.equals(header)) {
                items[i] = null;
                nullPushInEnd();
                initMessageAboutDelete();
                this.position--;
            }
        }
    }

    // init message for User : Item delete success
    private void initMessageAboutDelete() {
        this.message = "Task have been deleted.";
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
        return this.arrPrint;
    }

    // Array save all headers(without null).
    private Item[] arrPrint = new Item[0];

    // recording values in arrHeaders without nulls
    private void initArrPrint() {
        Item[] arrPrint = new Item[position];
        for (int i = 0; i != arrPrint.length; i++) {
            arrPrint[i] = this.items[i];
        }
        this.arrPrint = arrPrint;
    }

    /**
     * Filter Reverse Order
     * @see TrackerTest#whenMethodWorkThenReturnListHeadersInReverseOrder()
     * @return header list for out to User with Reverse Order
     */
    public Item[] getArrPrintFilter() {
        initArrPrint();
        Item[] arrPrintFilter = new Item[this.arrPrint.length];
        for (int i = 0, j = (arrPrintFilter.length - 1);
             (i != arrPrintFilter.length) && (j != -1); i++, j--) {
            arrPrintFilter[i] = this.arrPrint[j];
        }
        return arrPrintFilter;
    }
}