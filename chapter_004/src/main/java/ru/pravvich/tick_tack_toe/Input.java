package ru.pravvich.tick_tack_toe;

import java.util.Scanner;

/**
 * Console in.
 */
public class Input implements In {

    /**
     * Input stream to console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Console input for string.
     * @return input string.
     */
    @Override
    public String getStrInput() {
        return this.scanner.next();
    }

    /**
     * Console input for int.
     * @return input int.
     */
    @Override
    public int getNumInput() {
        return this.scanner.nextInt();
    }
}
