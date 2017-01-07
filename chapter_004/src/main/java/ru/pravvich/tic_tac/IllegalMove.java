package ru.pravvich.tic_tac;

/**
 * Exception for illegal move
 */
public class IllegalMove extends Exception {
    /**
     * Default constructor.
     * @param massage for print is stack trace.
     */
    public IllegalMove(String massage) {
        super(massage);
    }
}
