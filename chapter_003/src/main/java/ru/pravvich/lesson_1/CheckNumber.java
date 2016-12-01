package ru.pravvich.lesson_1;

import java.io.*;

class CheckNumber {

    boolean isNumber(InputStream in) {
        try (InputStream input = in; Scanner scanner = new Scanner(input)) {
            String str = scanner.nextLine();
            int value;
            if (str.matches("[+-]?\\d+")) {
                value = Integer.parseInt(str);
            } else {
                System.out.println("Это не целое число!");
                return false;
            }

            if ((value % 2) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
