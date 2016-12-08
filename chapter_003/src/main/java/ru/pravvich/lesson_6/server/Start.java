package ru.pravvich.lesson_6.server;

import java.io.File;

public class Start {
    public static void main(String[] args) {
        Input input = new Input();
        String start = "/Users/pavel/Desktop/test";
        input.select(new File(start));
    }
}
