package ru.pravvich.lesson_1;

import java.io.*;
import java.util.Scanner;

class CheckNumber {

    boolean isNumber(InputStream in) {
        try (InputStream input = in; Scanner scanner = new Scanner(input)) {
            String str = scanner.nextLine();
            int value;
            /*
             * сначала идёт проверка первого знака +- а может и не быть,
             * потом идёт \d - обозначение целых чисел,
             * + это условно неограниченное число этих целых чисел
             * в рамках формата integer
             * второй слэш - э
             * это специфика Java
             * так как с одним слэшем компилятор не поймёт
             *
             * а дабл так [+-]?\\d*\\.?\\d+
             */
            if (str.matches("[+-]?\\d+")) {
                value = Integer.parseInt(str);
            } else {
                System.out.println("Это не целое число!");
                return false;
            }
            return (value % 2) == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
