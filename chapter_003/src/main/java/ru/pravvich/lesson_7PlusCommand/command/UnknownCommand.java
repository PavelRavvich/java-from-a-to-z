package ru.pravvich.lesson_7PlusCommand.command;

import java.util.Scanner;

public class UnknownCommand implements Command {
    @Override
    public String getName() {
        return "-1";
    }

    @Override
    public void invoke(Scanner scanner) {
        System.err.println("Неизвестная команда.");
        printManual();
    }

    private static void printManual() {
        System.out.println(
                "Искать файл по полному имени: \"-f\"\nИскать файл по регулярному выражению: \"-r\"\nЗаписать файл: \"-o\"");
    }
}
