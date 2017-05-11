package ru.pravvich.start.commands;

import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Edition comment.
 */
public class EditionComment extends CommandState {

    public EditionComment(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        String oldCommit = this.input.ask("Enter old commit for edit");
        String newCommit = this.input.ask("Enter new commit");
        this.tracker.editionCommit(oldCommit, newCommit);
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(final String commandFlag) {
        return commandFlag.equals("e -c");
    }
}
