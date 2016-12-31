package ru.pravvich.tick_tack_toe.Users;

import java.util.Scanner;

public class Input implements In {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getStrInput() {
        return this.scanner.next();
    }

    @Override
    public int getNumInput() {
        return this.scanner.nextInt();
    }
}
