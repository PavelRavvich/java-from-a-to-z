package ru.pravvich.tic_tac.input;

/**
 * Determines dialog to user by console in-out.
 */
public interface DialogAsk {
    /**
     * Print question and get answer from console in.
     * @param question for print.
     * @return string is user's answer.
     */
    String askStr(String question);
    /**
     * Print question and get answer from console in.
     * @param question for print.
     * @return int number is user's answer.
     */
    int askNum(String question);
}
