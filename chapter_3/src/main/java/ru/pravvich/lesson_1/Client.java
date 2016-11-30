package ru.pravvich.lesson_1;

import java.io.InputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        Scanner scanner = new Scanner(System.in);
        String strCheck;
        do {
            System.out.println("Проверяем является ли целое число четным:");
            strCheck = scanner.nextLine();
            if (strCheck.matches("([1-9]){1}[0-9]{0,}")) {
                InputStream input = checkNumber.getInput(strCheck);
                System.out.println(checkNumber.isNumber(input));
            } else {
                System.out.println("Введенный символ не является целочисленным литералом.");
            }
        } while (!"q".equals(strCheck));
    }
}
