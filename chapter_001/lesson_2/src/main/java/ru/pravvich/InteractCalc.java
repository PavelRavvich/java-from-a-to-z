package ru.pravvich;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * InteractCalc implement user interface for all actions.
 */
public class InteractCalc {

    /**
     * Calculate calculate is contain all method action
     * and var result action.
     */
    private Calculate calculate = new Calculate();

    /**
     * Scanner for read input stream for command line.
     */
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Help.HELP.getContent());
        new InteractCalc().start();
    }

    /**
     * Read type double from command line.
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
     * @return String is result write.
     */
    private String nextAction() {
        return this.scanner.next();
    }

    /**
     * Move loop menu for user. If action equal "q" loop is end.
     */
    private void start() {

        System.out.println(Help.FIRST.getContent());
        this.calculate.setResult(this.nextDouble());
        System.out.println(Help.ACTION.getContent());
        String action = this.nextAction();

        while (!"q".equals(action)) {

            if ("+".equals(action)) {
                System.out.println(Help.SECOND.getContent());
                this.calculate.add(this.calculate.getResult(), this.nextDouble());
                System.out.println(this.calculate.getResult());

            } else if ("-".equals(action)) {
                System.out.println(Help.SECOND.getContent());
                this.calculate.subtraction(this.calculate.getResult(), this.nextDouble());
                System.out.println(this.calculate.getResult());

            } else if ("*".equals(action)) {
                System.out.println(Help.SECOND.getContent());
                this.calculate.multiplication(this.calculate.getResult(), this.nextDouble());
                System.out.println(this.calculate.getResult());

            } else if ("/".equals(action)) {
                System.out.println(Help.SECOND.getContent());
                this.calculate.div(this.calculate.getResult(), this.nextDouble());
                System.out.println(this.calculate.getResult());

            } else if ("c".equals(action) || "C".equals(action)) {
                System.out.println(Help.FIRST.getContent());
                this.calculate.setResult(this.nextDouble());

            } else {
                System.out.println(Help.NOEXIST.getContent());
            }

            System.out.println(Help.ACTION.getContent());
            action = this.nextAction();
        }
    }
}
