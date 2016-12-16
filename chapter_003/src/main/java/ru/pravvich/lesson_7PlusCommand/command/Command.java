package ru.pravvich.lesson_7PlusCommand.command;

import java.util.Scanner;

public interface Command {
    String getName();
    void invoke(Scanner scanner);
}
