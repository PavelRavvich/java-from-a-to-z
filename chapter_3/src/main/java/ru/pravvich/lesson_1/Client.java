package ru.pravvich.lesson_1;

import java.io.StringBufferInputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        Scanner scanner = new Scanner(System.in);
        String q = scanner.nextLine();
        boolean result = checkNumber.isNumber(new StringBufferInputStream(q));
        System.out.println(result);
    }
}
