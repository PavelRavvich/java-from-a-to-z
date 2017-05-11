package ru.pravvich.start.commands;

import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Find task By Header.
 */
public class FindByHeader extends CommandState {

    public FindByHeader(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        String header = this.input.ask("Enter task name for search.");
        this.tracker.findByHeader(header);
        System.out.println(tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(final String commandFlag) {
        return commandFlag.equals("f -h");
    }
}
