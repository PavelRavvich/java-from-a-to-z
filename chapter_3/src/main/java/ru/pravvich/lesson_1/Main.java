package ru.pravvich.lesson_1;

import java.util.Scanner;

public class Main {
    private int valueCheck;

    private String scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void check() {
        try {
            this.valueCheck = Integer.parseInt(this.scanner());
            if (this.valueCheck % 2 == 0) {
                System.out.println(String.format("%s %s", this.valueCheck,"- четное число."));
            } else {
                System.out.println(String.format("%s %s", this.valueCheck,"- не четное число."));
            }

        } catch (NumberFormatException ex) {
            System.out.println("Это не целое число!");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.check();
    }
}
