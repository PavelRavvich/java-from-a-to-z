package ru.pravvich.action;

import ru.pravvich.Calculate;
import ru.pravvich.Flags;
import ru.pravvich.Help;
import ru.pravvich.InteractCalc;

import java.util.Scanner;

/**
 * Realised action multiplication.
 */
public class Multi implements Action {
    @Override
    public String name() {
        return Flags.MUL.getFlag();
    }

    @Override
    public void invoke(Scanner scanner) {
        Calculate calculate = new Calculate();
        System.out.println(Help.SECOND.getContent());
        calculate.multiplication(InteractCalc.result, scanner.nextDouble());
        System.out.println(InteractCalc.result = calculate.getResult());
    }
}
