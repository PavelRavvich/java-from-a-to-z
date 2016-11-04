package ru.pravvich.start;

import ru.pravvich.models.*;

public class StartUI {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        String header = input.ask("Please enter the task name");
        Tracker tracker = new Tracker();
        tracker.add(new Task(header));
    }
}
