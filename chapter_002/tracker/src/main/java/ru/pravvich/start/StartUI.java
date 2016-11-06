package ru.pravvich.start;

import ru.pravvich.models.*;

/**
 * @since Pavel Ravvich
 * @since 04.11.2016
 * @see #add() addition task
 * @see #updateItem() replace task on new
 * @see #findByHeader()
 * @see #findById()
 * @see #deleteTask()
 * @see #addCommit()
 * @see #editionCommit()
 * @see #viewAllTasks()
 */
public class StartUI {
    private Input input;
    private Tracker tracker = new Tracker();

    public StartUI(Input input) {
        this.input = input;
    }

    public void editionCommit() {
        String oldCommit = input.ask("Enter old commit for edit");
        String newCommit = input.ask("Enter new commit");
        this.tracker.editionCommit(oldCommit, newCommit);
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    public void addCommit() {
        int id = input.askInt("Enter ID task for commit.");
        String commit = input.ask("Enter commit for this task.");
        this.tracker.addCommit(this.tracker.findById(id), commit);
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    // init new task
    public void add() {
        String header = this.input.ask("Please enter the task name");
        this.tracker.add(new Task(header));
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    public void updateItem() {
        String header = this.input.ask("Enter new name for task.");
        int id = this.input.askInt("Enter task ID for replace");
        Item item = new Item(header, id);
        this.tracker.updateItem(item);
        System.out.println(tracker.getMessage());
        System.out.println("=========================================================");
    }

    public void findById () {
        int id = this.input.askInt("Enter ID number");
        Item item = this.tracker.findById(id);
        System.out.println(tracker.getMessage() +  ": " + item.getHeader());
        System.out.println("=========================================================");
    }

    public void findByHeader() {
        String header = this.input.ask("Enter task name for search.");
        this.tracker.findByHeader(header);
        System.out.println(tracker.getMessage());
        System.out.println("=========================================================");
    }

    public void deleteTask() {
        String header = this.input.ask("Please enter the task name for delete");
        int id = this.tracker.findByHeader(header).getId();
        Item itemForDelete = this.tracker.findById(id);
        this.tracker.delete(itemForDelete);
        System.out.println(tracker.getMessage());
        System.out.println("=========================================================");
    }

    public void viewAllTasks() {
        String answer = this.input.ask("View all tasks : view -a" + "\n" +
                "View all tasks with revers filter : view -f");
        if (answer.equals("view -a")) {
            Item[] print = this.tracker.getPrintArray();
            System.out.println(this.tracker.getMessage());
            for (Item item : print) {
                System.out.println(item.getHeader() + "  ID: " + item.getId());
            }
            System.out.println("=========================================================");
        } else if (answer.equals("view -f")) {
            Item[] print = this.tracker.getArrPrintFilter();
            System.out.println(this.tracker.getMessage());
            for (Item item : print) {
                System.out.println(item.getHeader() + "  ID: " + item.getId());
            }
            System.out.println("=========================================================");
        } else {
            System.out.println("Error: command not found");
            System.out.println("=========================================================");
        }
    }

    public void startApp() {
        viewMenu();
        while (this.start) {
            String answer = this.input.ask("Enter the command: ");

            if (answer.equals("n -t")) {
                add();
            } else if (answer.equals("v")) {
                viewAllTasks();
            } else if (answer.equals("n -c")) {
                addCommit();
            } else if (answer.equals("e -c")) {
                editionCommit();
            } else if (answer.equals("e -t")) {
                updateItem();
            } else if (answer.equals("f -id")) {
                findById();
            } else if (answer.equals("f -h")) {
                findByHeader();
            } else if (answer.equals("d -t")) {
                deleteTask();
            } else if (answer.equals("q")) {
                this.start = false;
            } else if (answer.equals("help")) {
                viewMenu();
            } else {
                System.out.println("command not found");
            }
        }
    }

    private boolean start = true;

    public void viewMenu() {
        System.out.println("It supports the possibility: " + " \n" + "1. Add task: n -t" + "\n"
                + "2. View all tasks: v" + "\n" + "3. Add comment: n -c" + "\n" + "4. Edition comment: e -c" + "\n" +
                "5. Edition task: e -t" + "\n" + "6. Find bi ID: f -id" + "\n" + "7. Find by header: f -h" + "\n" +
                "8. Delete task: d -t" + "\n" + "9. Quit: q");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to task manager! " + "\n" + "For view manual enter: help");
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input);
        startUI.startApp();
    }
}
