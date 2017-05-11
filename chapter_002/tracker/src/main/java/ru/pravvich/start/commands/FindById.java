package ru.pravvich.start.commands;

import ru.pravvich.models.Item;
import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Find Item By Id.
 */
public class FindById extends CommandState {

    public FindById(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        int id = this.input.askInt("Enter ID number");
        final Item item = this.tracker.findById(id);
        System.out.println(item.getHeader());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(String commandFlag) {
        return commandFlag.equals("f -id");
    }
}
