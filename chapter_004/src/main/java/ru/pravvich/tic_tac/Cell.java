package ru.pravvich.tic_tac;

/**
 * Determines all imposable values which contain desk.
 */
public enum Cell {
    EMPTY(" "), X("x"), O("o");

    private String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
