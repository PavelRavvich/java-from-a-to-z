package ru.pravvich.lesson_5;

import java.util.Scanner;

public class StartApp {
    public static void main(String[] args) {
        System.out.println(Constants.GREETING.getValue());
        StartApp startApp = new StartApp();
        startApp.start();
    }

    private void start() {
        Chat chat = new Chat();
        String protocolLog = ">>>This is log :<<<";
        String phrase = "";
        boolean botOn = true;
        while (!phrase.equals(Constants.FINISH.getValue())) {
            phrase = new Scanner(System.in).nextLine();
            if (phrase.equals(Constants.FINISH.getValue())) break;
            botOn = chat.select(phrase, botOn);
            protocolLog = String.format("%s\n%s\n%s", protocolLog, phrase, chat.getBotAnswer());
        }
        chat.bigBrotherSee(String.format("%s\n%s\n%s", protocolLog, Constants.FINISH.getValue(), ">>>Log is end<<<"));
    }
}