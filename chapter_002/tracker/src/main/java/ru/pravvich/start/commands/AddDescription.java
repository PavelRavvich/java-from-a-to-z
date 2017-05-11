package ru.pravvich.start.commands;

import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Command add description.
 */
public class AddDescription extends CommandState {

    public AddDescription(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        int id = this.input.askInt("Enter Id need task:");
        String description = this.input.ask("Enter description");
        this.tracker.addDescription(id, description);
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(String commandFlag) {
        return commandFlag.equals("n -d");
    }
}
