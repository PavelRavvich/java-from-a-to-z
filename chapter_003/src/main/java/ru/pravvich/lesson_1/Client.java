package ru.pravvich.lesson_1;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        Scanner scanner = new Scanner(System.in);
        String q = scanner.nextLine();
        boolean result = checkNumber.isNumber(new ByteArrayInputStream(q.getBytes()));
        System.out.println(result);
    }
}
