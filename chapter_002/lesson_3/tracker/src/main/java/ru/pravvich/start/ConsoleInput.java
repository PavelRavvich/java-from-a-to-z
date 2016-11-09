package ru.pravvich.start;

import java.util.Scanner;

public class ConsoleInput implements Input {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    @Override
    public int askInt(String question) {
        System.out.println(question);
        String forParse = this.scanner.nextLine();
        if (forParse.length() != 0 && !(forParse.contains(" ")) && forParse.matches("[0-9]+")) {
            int ask = Integer.parseInt(forParse);
            return ask;
        }
        return -1;
    }
}
