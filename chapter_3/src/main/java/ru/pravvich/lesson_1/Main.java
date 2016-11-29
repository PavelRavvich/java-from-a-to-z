package ru.pravvich.lesson_1;

public class Main {

    public static void main(String[] args) {
        Input input = new Input();
        Check check = new Check();
        boolean result = check.checkInput(input);
        System.out.println(result);
    }
}
