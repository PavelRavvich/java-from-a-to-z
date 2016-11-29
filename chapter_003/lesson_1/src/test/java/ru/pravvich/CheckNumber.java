package ru.pravvich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckNumber {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    public boolean checkNumber(BufferedReader in) {
        try {
            if ((in.read() % 2) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        CheckNumber checkNumber = new CheckNumber();
        boolean ans = checkNumber.checkNumber(checkNumber.getBufferedReader());
        if (ans) {
            System.out.println("Четное.");
        } else {
            System.out.println("Не четное.");
        }
    }
}
