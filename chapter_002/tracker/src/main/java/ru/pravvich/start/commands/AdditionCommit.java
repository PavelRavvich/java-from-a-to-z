package ru.pravvich.start.commands;

import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Add comment.
 */
public class AdditionCommit extends CommandState {
    public AdditionCommit(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        int id = this.input.askInt("Enter ID task for commit.");
        String commit = this.input.ask("Enter commit for this task.");
        this.tracker.addCommit(this.tracker.findById(id), commit);
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(final String commandFlag) {
        return commandFlag.equals("n -c");
    }
}
