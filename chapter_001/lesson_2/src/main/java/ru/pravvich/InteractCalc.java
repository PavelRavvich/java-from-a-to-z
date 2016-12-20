package ru.pravvich;

import ru.pravvich.action.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * InteractCalc implement user interface for all actions.
 */
public class InteractCalc {

    /**
     * Contain current result action.
     * When invoke Reset (user enter flag - "c") then value update
     */
    public static double result;

    /**
     * Scanner for read input stream for command line.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * Contain all actions this program.
     */
    private ArrayList<Action> actions = new ArrayList<>();

    /**
     * Default constructor.
     */
    private InteractCalc() {
        this.initActions();
    }

    public static void main(String[] args) {
        System.out.println(Help.HELP.getContent());
        new InteractCalc().start();
    }

    /**
     * Read type double from command line.
     *
     * @return double is result write. If result read no number return 0;
     */
    private double nextDouble() {
        try {
            return this.scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println(Help.ERROR.getContent());
        }
        return 0d;
    }

    /**
     * Read type String from command line.
     *
     * @return String is result write.
     */
    private String nextAction() {
        return this.scanner.next();
    }

    /**
     * Init ArrayList all type actions.
     */
    private void initActions() {
        this.actions.add(new Sinus());
        this.actions.add(new Reset());
        this.actions.add(new Addit());
        this.actions.add(new Divis());
        this.actions.add(new Subst());
        this.actions.add(new Multi());
    }

    /**
     * Move loop menu for user. If action equal "q" loop is end.
     * МОЖНО БЫЛО КОНЕЧНО ВЕСЬ КОД ПЕРЕД ЦИКЛОМ ВЫНЕСТИ НО МНЕ ПОКАЗАЛОСЬ
     * ЧТО И ТАК НОРМАЛЬНО ЭТО ОДИН СМЫСЛ
     */
    private void start() {

        System.out.println(Help.FIRST.getContent());
        result = this.nextDouble();
        System.out.println(Help.ACTION.getContent());
        String action = this.nextAction();

        while (!"q".equals(action)) {
            this.actionExist(action);

            for (Action act : this.actions) {
                if (act.name().equals(action)) {
                    act.invoke(this.scanner);
                    break;
                }
            }

            System.out.println(Help.ACTION.getContent());
            action = this.nextAction();
        }
    }

    /**
     * Check flag exist in list actions.
     *
     * @param action name action for check.
     */
    private void actionExist(String action) {
        int count = 0;
        Flags[] flags = Flags.values();
        for (Flags item : flags) {
            if (!action.equals(item.getFlag())) {
                count++;
            }
        }

        if (count == Flags.values().length) {
            System.out.println(Help.NOEXIST.getContent());
        }
    }
}
