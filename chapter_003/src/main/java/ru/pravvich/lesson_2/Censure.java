package ru.pravvich.lesson_2;

import java.io.*;
import java.util.Scanner;

public class Censure {

    void dropAbuses(InputStream src, OutputStream dst, String[] words) {
        try (InputStream sours = src;
             OutputStream dest = dst;
             Scanner in = new Scanner(sours)) {

            // разбиваем на массив слов
            String[] check = in.nextLine().split(" ");
            // собераем строку обратно
            StringBuilder sb = new StringBuilder();

            // записываем не запрещенные слова
            for (int i = 0; i != check.length; i++) {
                for (int j = 0; j != words.length; j++) {
                    if (!check[i].equals(words[j])) {
                        sb.append(check[i]).append(" ");
                    }
                }
            }
            dest.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}