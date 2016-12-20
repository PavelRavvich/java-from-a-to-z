package ru.pravvich.action;

import ru.pravvich.EngineerCalculate;
import ru.pravvich.Flags;
import ru.pravvich.InteractCalc;

import java.util.Scanner;

/**
 * Realised action sinus.
 */
public class Sinus implements Action {
    @Override
    public String name() {
        return Flags.SIN.getFlag();
    }

    @Override
    public void invoke(Scanner scanner) {
        EngineerCalculate calculate = new EngineerCalculate();
        calculate.sinus(InteractCalc.result);
        System.out.println(InteractCalc.result = calculate.getResult());
    }
}
