package ru.pravvich.lesson_7PlusCommand.command;

import java.util.Scanner;

public class Help implements Command {
    @Override
    public String getName() {
        return "h";
    }

    @Override
    public void invoke(Scanner scanner) {
        System.out.println(HelpText.MANUAL.getText());
    }
}
