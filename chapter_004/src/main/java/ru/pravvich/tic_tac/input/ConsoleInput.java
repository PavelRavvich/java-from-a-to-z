package ru.pravvich.tic_tac.input;

import java.util.Scanner;

public class ConsoleInput implements Input{
    private Scanner scanner = new Scanner(System.in);

    private String input() {
        return scanner.next();
    }

    // ввод строк с консоли
    @Override
    public String getString() {
        return input();
    }

    // ввод цифр с консоли
    @Override
    public int getNumber() {
        String number = input();
        if (number.matches("[0-9]+")) {
            return Integer.parseInt(number);
        } else {
            System.err.println("Illegal argument.");
            return getNumber();
        }
    }
}
