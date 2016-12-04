package ru.pravvich.lesson_5;

import java.util.Scanner;

public class StartApp {
    private void start() {
        Chat chat = new Chat("/Users/pavel/Desktop/answer.txt");
        String protocol = ">>>This is log:<<<";
        String phrase = "";
        boolean botOn = true;
        while (!phrase.equals("end")) {
            phrase = new Scanner(System.in).nextLine();
            if (phrase.equals("end")) break;
            botOn = chat.select(phrase,botOn);
            protocol = String.format("%s\n%s\n%s",protocol,phrase,chat.getBotAnswer());
        }
        chat.bigBrotherSee(protocol);
    }

    public static void main(String[] args) {
        System.out.println("Welcome in chat! \nEnter you message:");
        StartApp startApp = new StartApp();
        startApp.start();
    }
}
