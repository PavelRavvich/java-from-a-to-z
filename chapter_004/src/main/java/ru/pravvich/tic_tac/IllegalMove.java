package ru.pravvich.tic_tac;

/**
 * Created by pavel on 04.01.17.
 */
public class IllegalMove extends Exception {
    public IllegalMove(String massage) {
        super(massage);
    }
}
