package ru.pravvich.start.commands;

import ru.pravvich.start.Input;
import ru.pravvich.start.Tracker;

/**
 * Created by pavel on 11.05.17.
 */
public abstract class CommandState implements Command {
    final Tracker tracker;
    final Input input;

    public CommandState(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }


}
