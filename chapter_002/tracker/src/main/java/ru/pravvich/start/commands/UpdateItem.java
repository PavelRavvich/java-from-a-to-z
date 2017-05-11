package ru.pravvich.start.commands;

import ru.pravvich.models.Item;
import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Update Item header by id.
 */
public class UpdateItem extends CommandState {

    public UpdateItem(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        String header = this.input.ask("Enter new name for task.");
        int id = this.input.askInt("Enter task ID for replace");
        Item item = new Item(header, id);
        this.tracker.updateItem(item);
        System.out.println(tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(String commandFlag) {
        return commandFlag.equals("e -t");
    }
}
