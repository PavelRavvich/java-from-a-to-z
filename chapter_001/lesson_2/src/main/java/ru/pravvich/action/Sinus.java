package ru.pravvich.action;

import ru.pravvich.EngineerCalculate;

import java.util.Scanner;

public class Sinus implements Action {
    @Override
    public String name() {
        return "sin";
    }

    @Override
    public void invoke(Scanner scanner) {
        EngineerCalculate calculate = new EngineerCalculate();
        System.out.println("Введите число:");
        calculate.sinus(scanner.nextDouble());
    }
}
