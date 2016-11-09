package ru.pravvich.start;

/**
 * @since Pavel Ravvich
 * @since 09.11.2016
 */
public class StartUI {
    private Input input;
    private Tracker tracker = new Tracker();

    // only for tests
    public void setInput(Input input) {
        this.input = input;
    }

    public Tracker getTracker() {
        return this.tracker;
    }

    public StartUI(Input input) {
        this.input = input;
    }

    // init new task
    public void startUpp() {
        MenuTracker menuTracker = new MenuTracker(this.input,this.tracker);
        menuTracker.fillAction();
        menuTracker.showMenu();
        do {
            int key = this.input.askInt("Select action: ");
            menuTracker.select(key);
            menuTracker.showMenu();
        } while (!("y").equals(this.input.ask("For quit enter (y) : ")));
    }

    public static void main(String[] args) {

        System.out.println("Welcome to task manager!");
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input);
        startUI.startUpp();
    }
}