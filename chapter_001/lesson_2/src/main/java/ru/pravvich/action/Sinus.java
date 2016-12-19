package ru.pravvich.action;

import ru.pravvich.EngineerCalculate;
import ru.pravvich.InteractCalc;

import java.util.Scanner;

public class Sinus implements Action {
    @Override
    public String name() {
        return "sin";
    }

    @Override
    public void invoke(Scanner scanner) {
        EngineerCalculate calculate = new EngineerCalculate();
        calculate.sinus(InteractCalc.result);
    }
}
