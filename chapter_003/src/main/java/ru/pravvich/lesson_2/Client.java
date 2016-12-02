package ru.pravvich.lesson_2;

import java.io.*;

public class Client {

    public static void main(String[] args) throws IOException {
        Censure censure = new Censure();
        ByteArrayInputStream in = new ByteArrayInputStream(
                "Не пойму я чем секрет жопа есть а слова нет?".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        censure.dropAbuses(in,out, new String[]{"жопа"});
        System.out.println(out.toString());
    }

}