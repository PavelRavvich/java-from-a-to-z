package ru.pravvich.start.commands;

import ru.pravvich.models.Item;
import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Delete Task.
 */
public class DeleteTask extends CommandState {

    public DeleteTask(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        String header = this.input.ask("Please enter the task name for delete");
        int id = this.tracker.findByHeader(header).getId();
        Item itemForDelete = this.tracker.findById(id);
        this.tracker.delete(itemForDelete);
        System.out.println(tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(final String commandFlag) {
        return commandFlag.equals("d -t");
    }
}
