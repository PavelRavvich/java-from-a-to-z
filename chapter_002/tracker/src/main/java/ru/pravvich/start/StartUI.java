package ru.pravvich.start;

import ru.pravvich.models.*;

public class StartUI {
    public static void main() {
        Tracker tracker = new Tracker();
        tracker.add(new Task("Pavel", 25));
    }
}
