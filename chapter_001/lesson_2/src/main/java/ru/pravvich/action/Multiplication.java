package ru.pravvich.action;

import ru.pravvich.Calculate;
import ru.pravvich.InteractCalc;

import java.util.Scanner;

public class Multiplication implements Action {
    @Override
    public String name() {
        return "*";
    }

    @Override
    public void invoke(Scanner scanner) {
        Calculate calculate = new Calculate();
        System.out.println("Введите второе:");
        calculate.multiplication(InteractCalc.result, scanner.nextDouble());
    }
}
