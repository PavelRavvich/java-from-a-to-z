package ru.pravvich.start.commands;

import ru.pravvich.models.Task;
import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Add task.
 */
public class AdditionTask extends CommandState {

    public AdditionTask(Tracker tracker, Input input) {
        super(tracker, input);
    }

    @Override
    public void execute() {
        String header = this.input.ask("Please enter the task name");
        this.tracker.add(new Task(header));
        System.out.println(this.tracker.getMessage());
        System.out.println("=========================================================");
    }

    @Override
    public boolean checkCommand(String commandFlag) {
        return commandFlag.equals("n -t");
    }
}
