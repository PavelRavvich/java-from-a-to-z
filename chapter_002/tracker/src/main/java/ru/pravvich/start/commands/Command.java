package ru.pravvich.start.commands;

/**
 * Select command from user input.
 */
public interface Command {

    /**
     * Execute action.
     */
    void execute();

    /**
     * Select command by flag.
     * @param commandFlag for select.
     * @return true if flag equals, else false.
     */
    boolean checkCommand(final String commandFlag);
}
