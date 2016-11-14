package ru.pravvich.start;

/**
 * @since Pavel Ravvich
 * @since 09.11.2016
 */
public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] userAction = new UserAction[11];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    int position = 0;

    public void addActions(UserAction action) {
        this.userAction[this.position] = action;
        this.position++;
    }

    public void select(int key) throws IndexOutOfBoundsException {
            this.userAction[key].execute(this.input, this.tracker);
    }

    public void showMenu() {
        for (UserAction action : this.userAction) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}