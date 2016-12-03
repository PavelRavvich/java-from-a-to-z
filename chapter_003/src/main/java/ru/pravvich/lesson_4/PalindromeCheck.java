package ru.pravvich.lesson_4;

import java.util.Scanner;

class PalindromeCheck {
    void check() {
        System.out.println("Введите слово из пяти букв:");
        try (Scanner scanner = new Scanner(System.in)) {
            String[] arr = scanner.nextLine().split("");
            if ((arr.length == 5) &&
                    (0 == arr[0].compareToIgnoreCase(arr[4])) &&
                    (0 == arr[1].compareToIgnoreCase(arr[3]))
                    ) {
                System.out.println("это палиндром.");
            } else {
                System.out.println("это не палиндром.");
            }
        }
    }
}
