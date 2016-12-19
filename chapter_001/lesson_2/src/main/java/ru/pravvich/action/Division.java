package ru.pravvich.action;

import ru.pravvich.Calculate;

import java.util.Scanner;

public class Division implements Action {
    @Override
    public String name() {
        return "/";
    }

    @Override
    public void invoke(Scanner scanner) {
        Calculate calculate = new Calculate();
        System.out.println("Введите первое число:");
        double fst = scanner.nextDouble();
        System.out.println("Введите второе:");
        calculate.div(fst, scanner.nextDouble());
    }
}
