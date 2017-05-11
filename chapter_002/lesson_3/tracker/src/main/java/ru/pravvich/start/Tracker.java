package ru.pravvich.start;

import ru.pravvich.models.Item;

import java.util.Random;

/**
 * Users class
 * @author Pavel Ravvich 01.11.2016
 * @author version 1.0
 * @see #addDescription(int, String)
 * @see #updateItem(Item) +
 * @see #addCommit(Item, String) - Addition commit
 * @see #editionCommit(String, String)
 * @see #deleteCommit(String) +
 * @see #findById(int)
 * @see #add(Item) addition new Item
 * @see #findByHeader(String)
 * @see #generateId()
 * @see #getMessage()
 * @see #delete(Item) delete Item
 * @see #getPrintArray() array Item for print for user
 * @see #getArrPrintFilter() print with filter revers order
 */
public class Tracker {
    Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();
    // for messages
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    Item[] getItems() {
        return this.items;
    }

    int getPosition() {
        return this.position;
    }

    /**
     * add description in item
     * @param id need item
     */
    void addDescription(final int id, final String description) {
        if (description.length() != 0) {
            for (Item item : this.items) {
                if (item != null && item.getId() == id) {
                    item.setDescription(description);
                    this.message = String.format("%s %s %s %s %s", "Description:",
                            description, "add in", item.getHeader(), "success.");
                }
            }
        } else {
            this.message = "Fail. Description empty.";
        }
    }

    /**
     * @param item - item for update
     */
    void updateItem(final Item item) {
        for (int i = 0; i != this.position; i++) {
            if (item != null && this.items[i].getId() == item.getId()) {
                final String oldName = this.items[i].getHeader();
                this.items[i] = item;
                this.message = String.format("%s %s %s %s %s", "Update", oldName, "on",
                        this.items[i].getHeader(), "success");
                break;
            } else {
                this.message = "The task with Id does not exist.";
            }
        }
    }

    /**
     * Delete task (null replacement)
     * @param item - for deleted object
     * @see #nullPushInEnd() - using SWAP for new null
     */
    void delete(final Item item) {
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getId() == item.getId()) {
                this.items[i] = null;
                nullPushInEnd();
                this.position--;
                this.message = "Task have been deleted.";
                break;
            } else {
                this.message = "Task not found.";
            }
        }
    }

    /**
     * Addition item in items array
     * @param item new item for init in array
     */
    void add(final Item item) {
        if (item != null && item.getHeader().length() > 1) {
            item.setId(generateId());
            this.items[this.position] = item;
            this.position++;
            this.message = String.format("%s %s\n%s %s", "Create new task: ",item.getHeader(), "ID :", item.getId());
        } else {
            this.message = "Add fail. Enter name task require";
        }
    }

    Item findByHeader(final String header) {
        Item result = new Item();
        result.setHeader("does not exist");
        for (Item item : this.items) {
            if (item != null && item.getHeader().equals(header)) {
                result = item;
                if (result.getDescription() == null) {
                    result.setDescription("Does not description");
                }
                this.message = String.format("%s\n%s %s\n%s %s\n%s %s", " Find: ", "Task name: ",
                        item.getHeader(), " ID: ", item.getId(), " Description: ", item.getDescription());
                break;
            } else {
                this.message = "The task does not exist. Please try again.";
            }
        }
        return result;
    }

    /**
     * Addition commit in ArrayList commits
     * @param item for find needed item
     * @param commit - commit for add
     */
    void addCommit(final Item item, final String commit) {
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getId() == item.getId() && commit != null) {
                item.getCommits().add(commit);
                this.message = "Commit successfully added";
            }
        }
    }

    /**
     * Replace oldCommit on newCommit
     */
    void editionCommit(final String oldCommit, final String newCommit) {
        for (int i = 0; i != this.position; i++) {
            for (int j = 0; j != this.items[i].getCommits().size(); j++) {
                String commit = this.items[i].getCommits().get(j);
                if (oldCommit.equals(commit)) {
                    this.items[i].getCommits().remove(j);
                    this.items[i].getCommits().add(j, newCommit);
                    this.message = String.format("%s%s%s %s%s%s\n%s %s %s %s","«",oldCommit,
                            "» success replace on","«",newCommit,"»","For ",this.items[i].getHeader(),
                            " ID : ",this.items[i].getId());
                }
            }
        }
    }

    /**
     * @param commit - Commit For Delete
     */
    void deleteCommit(final String commit) {
        for (int i = 0; i != this.position; i++) {
            for (int j = 0; j != this.items[i].getCommits().size(); j++) {
                if (commit.equals(this.items[i].getCommits().get(j))) {
                    this.items[i].getCommits().remove(j);
                    this.message = "Commit successfully delete.";
                    break;
                } else {
                    this.message = "Commit not found.";
                }
            }
        }
    }

    /**
     * @param id - id on which we look for an item
     * @return - found by id item
     */
    Item findById(final int id) {
        Item result = new Item();
        result.setHeader("does not exist");
        for (Item item : this.items) {
            if (item != null && item.getId() == id) {
                result = item;
                if (item.getDescription() != null) {
                    this.message = String.format("%s\n%s\n%s %s", "Find: ",
                            item.getHeader(),"Description:",item.getDescription());
                    break;
                } else {
                    this.message = String.format("%s\n%s\n%s %s", "Find: ",
                            item.getHeader(),"Description:","Description is empty.");
                    break;
                }
            } else {
                this.message = "The task with Id does not exist. Please try again.";
            }
        }
        return result;
    }

    /**
     * @return unique id
     */
    private int generateId() {
        int result = RN.nextInt() + ((int) System.currentTimeMillis());
        if (result < 0) {
            result *= -1;
        }
        return result;
    }

    /**
     * @return - init message for user
     */
    String getMessage() {
        return this.message;
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
     * @return arrPrint list for out to User
     */
    Item[] getPrintArray() {
        initArrPrint();
        this.message = String.format("%s %s %s", "We found",this.arrPrint.length,"tasks.");
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
     * @return Item list for out to User with Reverse Order
     */
    Item[] getArrPrintFilter() {
        initArrPrint();
        Item[] arrPrintFilter = new Item[this.arrPrint.length];
        for (int i = 0, j = (arrPrintFilter.length - 1);
             (i != arrPrintFilter.length) && (j != -1); i++, j--) {
            arrPrintFilter[i] = this.arrPrint[j];
        }
        this.message = String.format("%s %s %s","Reverse order filter. We found",this.arrPrint.length,"tasks");
        return arrPrintFilter;
    }


}