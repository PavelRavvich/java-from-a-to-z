package ru.pravvich.lesson_2;

import java.io.*;
import java.util.Scanner;

public class Censure {

    void dropAbuses(InputStream src, OutputStream dst, String[] words) {
        try (InputStream sours = src;
             OutputStream dest = dst;
             Scanner in = new Scanner(sours)) {

            String inner = in.nextLine();

            // разбиваем на массив слов
            inner = String.format("%s ",inner);
            String[] check = inner.split(" ");

            // присваиваем нули запещенным словам
            for (int i = 0; i != check.length; i++) {
                for (int j = 0; j != words.length; j++) {
                    if (check[i].equals(words[j])) {
                        check[i] = null;
                    }
                }
            }

            // считаем нули
            int count = 0;
            for (String str: check) {
                if (str == null) count++;
            }

            // избавляемся от нулей
            String[] withoutNull = new String[check.length - count];
            for (int i = 0, j = 0; i != check.length; i++) {
                if (check[i] != null) {
                    withoutNull[j] = check[i];
                    j++;
                }
            }

            // собераем строку обратно
            String resultCheck = "";
            for (int i = 0; i != withoutNull.length; i++) {
                if (i != 0) {
                    resultCheck = String.format("%s%s%s",resultCheck," ",withoutNull[i]);
                } else {
                    resultCheck = withoutNull[i];
                }
            }

            //отправляем в выходной поток
            byte[] bytes = resultCheck.getBytes();
            int i = 0;
            while (true) {
                if (i < bytes.length) {
                    dest.write(resultCheck.getBytes()[i]);
                    i++;
                } else {
                    break;
                }
            }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
