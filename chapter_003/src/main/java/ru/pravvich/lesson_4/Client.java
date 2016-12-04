package ru.pravvich.lesson_4;

public class Client {
    public static void main(String[] args) {
        PalindromeCheck pal = new PalindromeCheck();
        System.out.println(pal.check("топот"));
        System.out.println(pal.check("молот"));
        System.out.println(pal.check("пот"));
    }
}
