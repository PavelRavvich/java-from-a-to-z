package ru.pravvich.lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;

public class Client {
    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        String in = null;
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));) {
            in = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = checkNumber.isNumber(new StringBufferInputStream(in));
        System.out.println(result);
    }
}
