package ru.pravvich.tick_tack_toe.Users;

/**
 * Determines actions which colors.
 */
public interface GamersColors {

    /**
     * Determines color for this gamer.
     * @param color 'X' or 'Y'.
     */
    void setColor(char color);

    /**
     * Getter for gamer color.
     * @return color 'X' or 'Y'.
     */
    char getColor();
}
