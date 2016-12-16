package ru.pravvich.lesson_7PlusCommand.command;


import ru.pravvich.lesson_7PlusCommand.Dir;
import ru.pravvich.lesson_7PlusCommand.Find;

import java.util.Scanner;

public class SearchRegExp implements Command {

    @Override
    public String getName() {
        return "-r";
    }

    @Override
    public void invoke(Scanner scanner) {
        System.out.println(HelpText.DIRFORSEARCH.getText());
        Dir dir = new Dir(scanner.nextLine());
        System.out.println(HelpText.ENTERREGEXP.getText());
        Find.setTarget(dir.searchRegExp(scanner.nextLine()))
                .forEach(System.out::println);
    }
}
