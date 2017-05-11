package ru.pravvich.start;

/**
 * @since Pavel Ravvich
 * @since 09.11.2016
 */
class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] userAction = new UserAction[11];
    private int position = 0;

    MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    void addActions(UserAction action) {
        this.userAction[this.position] = action;
        this.position++;
    }

    void select(int key) throws IndexOutOfBoundsException {
            this.userAction[key].execute(this.input, this.tracker);
    }

    void showMenu() {
        for (UserAction action : this.userAction) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }
}