package ru.pravvich.lesson_1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.check();
    }

    private String scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void check() {
        try {
            int valueCheck = Integer.parseInt(this.scanner());
            if (valueCheck % 2 == 0) {
                System.out.println(String.format("%s %s", valueCheck, "- четное число."));
            } else {
                System.out.println(String.format("%s %s", valueCheck, "- не четное число."));
            }

        } catch (NumberFormatException ex) {
            System.out.println("Это не целое число!");
        }
    }
}
