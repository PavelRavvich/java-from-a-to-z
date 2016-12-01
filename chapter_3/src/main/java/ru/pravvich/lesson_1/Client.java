package ru.pravvich.lesson_1;

import java.io.InputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        Scanner scanner = new Scanner(System.in);
        String strCheck;
        do {
            System.out.println("Проверяем является ли целое однозначное число четным:");
            strCheck = scanner.nextLine();
            if (strCheck.matches("[0-9]+")) {
                InputStream input = checkNumber.getInput(strCheck);
                System.out.println(checkNumber.isNumber(input));
            } else if ("q".equals(strCheck)) {
                System.out.println("Выход из программы.");
            } else {
                System.out.println("Введенный символ не является однозначным целочисленным литералом.");
            }
        } while (!"q".equals(strCheck));
    }
}
