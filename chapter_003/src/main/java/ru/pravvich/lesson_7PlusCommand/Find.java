package ru.pravvich.lesson_7PlusCommand;

import ru.pravvich.lesson_7PlusCommand.command.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Find {

    private ArrayList<Command> commands = new ArrayList<>();

    private static ArrayList<String> target;

    public static ArrayList<String> getTarget() {
        return target;
    }

    public static ArrayList<String> setTarget(ArrayList<String> target) {
        return Find.target = target;
    }

    public static void main(String[] args) {
        new Find().start();
    }

    private void initCommands() {
        this.commands.add(new UnknownCommand());
        this.commands.add(new SearchRegExp());
        this.commands.add(new SearchByName());
        this.commands.add(new RecordResult());
        this.commands.add(new Help());
    }

    private void start() {
        this.initCommands();
        System.out.println(HelpText.HELP.getText());
        try (Scanner scanner = new Scanner(System.in)) {

            String massage = scanner.nextLine();

            while (!"q".equals(massage)) {
                for (Command command : this.commands) {
                    if (command.getName().equals(this.checkKey(massage))) {
                        command.invoke(scanner);
                        break;
                    }
                }

                massage = scanner.nextLine();
            }
        }
    }

    private String checkKey(String key) {
        if (!key.equals("-r") && !key.equals("-f") &&
                !key.equals("-o") && !key.equals("h")) {

            return "-1";
        }
        return key;
    }
}
