package ru.pravvich.tic_tac.validation;

import ru.pravvich.tic_tac.Cell;

/**
 * Determines base methods for validates statements desc.
 */
public interface Validation {

    /**
     * Determines winner
     *
     * @param desk - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    boolean winnerDetermines(Cell[][] desk);

    /**
     * Check exist empty cell.
     *
     * @param desk - desc for check.
     * @return true if we have empty cell. False if not empty cell.
     */
    boolean emptyCellExist(Cell[][] desk);

    /**
     * Check conditions for game can go on.
     *
     * @return true if winner do not determines and empty cell is exist.
     */
    boolean gameCanGoOn(Cell[][] desk);
}
