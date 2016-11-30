package ru.pravvich.lesson_1;

import java.io.InputStream;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        InputStream input = checkNumber.getInput(new Scanner(System.in).nextLine());
        System.out.println(checkNumber.isNumber(input));
    }
}
