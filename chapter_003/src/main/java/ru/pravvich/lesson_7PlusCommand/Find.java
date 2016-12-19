package ru.pravvich.lesson_7PlusCommand;

import ru.pravvich.lesson_7PlusCommand.command.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Find {

    /**
     * Target - for contain result paths before record in file
     */
    private static ArrayList<String> target;

    /**
     * Command - var for contain all action program.
     */
    private ArrayList<Command> commands = new ArrayList<>();

    /**
     * Default constructor - init all action in program.
     */
    private Find() {
        this.initCommands();
    }

    public static ArrayList<String> getTarget() {
        return target;
    }

    /**
     * Method init target.
     *
     * @param target - target list with result path.
     * @return - target without changes.
     */
    public static ArrayList<String> setTarget(ArrayList<String> target) {
        return Find.target = target;
    }

    public static void main(String[] args) {
        new Find().start();
    }

    /**
     * Method init all action program. Every action is class with
     * override method invoke(Scanner scanner).
     */
    private void initCommands() {
        this.commands.add(new UnknownCommand());
        this.commands.add(new SearchRegExp());
        this.commands.add(new SearchByName());
        this.commands.add(new RecordResult());
        this.commands.add(new Help());
    }

    /**
     * Open stream with console and loop menu.
     */
    private void start() {
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

    /**
     * Method check flag for command.
     * If user in known flag no action.
     *
     * @param key Key for identification which one of Command use.
     * @return If user enter unknowns command than return flag "-1".
     * "-1" activation class UnknownCommand.
     */
    private String checkKey(String key) {
        if (!key.equals("-r") && !key.equals("-f") &&
                !key.equals("-o") && !key.equals("h")) {

            return "-1";
        }
        return key;
    }
}
