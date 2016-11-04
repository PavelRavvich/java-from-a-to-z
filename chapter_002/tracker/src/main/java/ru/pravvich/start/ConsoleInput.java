package ru.pravvich.start;

import java.util.Scanner;

/**
 * Created by pavel on 04.11.16.
 */
public class ConsoleInput implements Input {

    Scanner scanner= new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.scanner.nextLine();
    }
}
