package ru.pravvich.start;

import ru.pravvich.jdbs.DBPropertyLoader;
import ru.pravvich.start.commands.*;

import java.util.ArrayList;
import java.util.List;

/**
 * StartApp.
 */
public class StartApp extends  StartUI {

    private final Input input;
    private final SQLTracker tracker;
    private final List<Command> commands;

    public StartApp(final SQLTracker tracker, final Input input) {
        super(input);
        this.commands = new ArrayList<>(100);
        this.tracker = tracker;
        this.input = input;
        this.initCommands();
    }

    private void initCommands() {
        this.commands.add(new AddDescription(this.tracker, this.input));
        this.commands.add(new AdditionCommit(this.tracker, this.input));
        this.commands.add(new EditionComment(this.tracker, this.input));
        this.commands.add(new AdditionTask(this.tracker, this.input));
        this.commands.add(new FindByHeader(this.tracker, this.input));
        this.commands.add(new UpdateItem(this.tracker, this.input));
        this.commands.add(new DeleteTask(this.tracker, this.input));
        this.commands.add(new FindById(this.tracker, this.input));
    }

    public void startApp() {

        super.viewMenu();

        boolean on = true;

        String answer;

        while (on) {

            answer = this.input.ask("Enter the command: ");

            if (answer.equals("q")) {
                this.tracker.closeConnection();
                on = false;
            }

            for (Command c : commands) {
                if (c.checkCommand(answer)) {
                    c.execute();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to task manager! \nFor view manual enter: help");
        Input input = new ConsoleInput();
        SQLTracker tracker = new SQLTracker(new DBPropertyLoader());
        StartUI startUI = new StartApp(tracker, input);
        startUI.startApp();
    }

}
