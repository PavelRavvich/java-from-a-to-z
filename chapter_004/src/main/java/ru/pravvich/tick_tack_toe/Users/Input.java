package ru.pravvich.tick_tack_toe.Users;

import java.util.Scanner;

public class Input {
    private Scanner scanner = new Scanner(System.in);

    public String getStrInput() {
        return this.scanner.next();
    }

    public int getNumInput() {
        return this.scanner.nextInt();
    }
}
