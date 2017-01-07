package ru.pravvich.tic_tac.input;

import java.util.Scanner;

/**
 * Class determines input steam from console.
 */
public class ConsoleInput implements Input{
    /**
     * Provides console in.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Call method to scanner for getting string stream from console.
     * @return string from console in.
     */
    private String input() {
        return scanner.next();
    }

    /**
     * Console input for strings.
     * @return string from user via console.
     */
    @Override
    public String getString() {
        return input();
    }

    /**
     * Console input for numbers.
     * @return number from user via console in.
     */
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
