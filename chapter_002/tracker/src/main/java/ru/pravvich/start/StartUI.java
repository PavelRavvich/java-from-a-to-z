package ru.pravvich.start;

import ru.pravvich.models.*;

public class StartUI {
    public static void main(String[] args) {
    	System.out.println("Программа запущена");
        Tracker tracker = new Tracker();
        tracker.add(new Task("Pavel", 25));
    }
}
