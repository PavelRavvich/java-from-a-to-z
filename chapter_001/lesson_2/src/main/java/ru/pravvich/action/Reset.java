package ru.pravvich.action;

import ru.pravvich.Flags;
import ru.pravvich.Help;
import ru.pravvich.InteractCalc;

import java.util.Scanner;

/**
 * Reset InteractCalc.result for new progression actions.
 */
public class Reset implements Action {
    @Override
    public String name() {
        return Flags.RES.getFlag();
    }

    @Override
    public void invoke(Scanner scanner) {
        System.out.println(Help.FIRST.getContent());
        InteractCalc.result = scanner.nextDouble();
    }
}
