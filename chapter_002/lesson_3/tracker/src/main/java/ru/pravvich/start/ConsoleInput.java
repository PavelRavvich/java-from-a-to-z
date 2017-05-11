package ru.pravvich.start;

import java.util.Scanner;

class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }

    @Override
    public int askInt(String question) {

        System.out.println(question);
        String forParse = this.scanner.nextLine();

        if (forParse.length() != 0 &&

                !(forParse.contains(" ")) &&

                forParse.matches("[0-9]+")

                ) {

            return Integer.parseInt(forParse);
        }

        return -1;

    }
}
