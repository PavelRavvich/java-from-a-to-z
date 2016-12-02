package ru.pravvich.lesson_2;

import java.io.*;

public class Client {

    public static void main(String[] args) throws IOException {
        //CensureInThread censureInThread = new CensureInThread();
        Censure censure = new Censure();
        ByteArrayInputStream in = new ByteArrayInputStream("В чем секрет жопа есть а слова нет?".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //censureInThread.dropAbuses(in,out,new String[]{"m"});
        censure.dropAbuses(in,out, new String[]{"жопа"});


        System.out.println(out.toString());
    }

}
