package ru.pravvich.lesson_2;

import java.io.*;
import java.util.ArrayList;
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

            ArrayList<String> withoutNull = new ArrayList<String>();

            // записываем не запрещенные слова
            for (int i = 0; i != check.length; i++) {
                for (int j = 0; j != words.length; j++) {
                    if (!check[i].equals(words[j])) {
                        withoutNull.add(check[i]);
                    }
                }
            }

            // собераем строку обратно
            String resultCheck = "";
            for (int i = 0; i != withoutNull.size(); i++) {
                if (i != 0) {
                    resultCheck = String.format("%s%s%s",resultCheck," ",withoutNull.get(i));
                } else {
                    resultCheck = withoutNull.get(i);
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
