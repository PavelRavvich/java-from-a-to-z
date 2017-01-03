package ru.pravvich.tick_tack_toe.desk;

/**
 * Determines base methods for validates statements desc.
 */
public interface Validation {

    /**
     * Determines winner
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    boolean winnerDetermines(char[][] desc);

    /**
     * Check exist empty cell.
     * @param desc - desc for check.
     * @return true if we have empty cell. False if not empty cell.
     */
    boolean emptyCellExist(char[][] desc);

    /**
     * Check conditions for game can go on.
     * @return true if winner do not determines and empty cell is exist.
     */
    boolean gameCanGoOn(char[][] desc);
}
