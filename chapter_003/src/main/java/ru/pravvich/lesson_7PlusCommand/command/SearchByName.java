package ru.pravvich.lesson_7PlusCommand.command;

import ru.pravvich.lesson_7PlusCommand.Dir;
import ru.pravvich.lesson_7PlusCommand.Find;

import java.util.Scanner;

public class SearchByName implements Command {

    @Override
    public String getName() {
        return "-f";
    }

    @Override
    public void invoke(Scanner scanner) {
        System.out.println(HelpText.DIRFORSEARCH.getText());
        Dir dir = new Dir(scanner.nextLine());
        System.out.println(HelpText.FILEFORSEARCH.getText());
        Find.setTarget(dir.searchByName(scanner.nextLine()))
                .forEach(System.out::println);
    }
}
