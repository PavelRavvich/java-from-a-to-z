package ru.pravvich.lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckNumber {

    public boolean checkNumber(BufferedReader in) {
        try {
            if ((in.read() % 2) == 0) return true;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        CheckNumber checkNumber = new CheckNumber();
        boolean ans = checkNumber.checkNumber(bufferedReader);
        if (ans) {
            System.out.println("Четное.");
        } else {
            System.out.println("Не четное.");
        }
    }
}