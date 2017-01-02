package ru.pravvich.tick_tack_toe;

/**
 * Determined colors.
 */
public enum Colors {
    X('X'), O('O');

    private char color;

    public char getColor() {
        return color;
    }

    Colors(char color) {
        this.color = color;
    }


}
