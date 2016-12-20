package ru.pravvich.action;

import java.util.Scanner;

/**
 * Interface describe the behavior of all actions program.
 */
public interface Action {

    /**
     * For identification action.
     * @return flag.
     */
    String name();

    /**
     * Execute action.
     * @param scanner for get arg from command line.
     */
    void invoke(Scanner scanner);
}
