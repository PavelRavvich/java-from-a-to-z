package ru.pravvich.tic_tac.validation;

import ru.pravvich.tic_tac.Cell;

/**
 * Util class determines validates statements desc.
 */
public class ValidationWinnerUtil implements Validation {

    /**
     * Check conditions for game can go on.
     *
     * @return true if winner do not determines and empty cell is exist.
     */
    @Override
    public boolean gameCanGoOn(Cell[][] desc) {
        return !this.winnerDetermines(desc) &&
                this.emptyCellExist(desc);
    }

    /**
     * Check exist empty cell.
     *
     * @param desc - desc for check.
     * @return true if we have empty cell. False if not empty cell.
     */
    @Override
    public boolean emptyCellExist(Cell[][] desc) {
        for (Cell[] aDesc : desc) {
            for (int j = 0; j < desc.length; j++) {
                if (Cell.EMPTY.equals(aDesc[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines winner
     *
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    @Override
    public boolean winnerDetermines(Cell[][] desc) {
        return this.validateVertical(desc) ||
                this.validateHorizontal(desc) ||
                this.validateDiagonals(desc);
    }

    /**
     * Validate winner by vertical.
     *
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    private boolean validateVertical(Cell[][] desc) {
        byte equal = 0;
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != (desc.length - 1); j++) {
                if (desc[i][j].equals(desc[i][j + 1]) &&
                        (!Cell.EMPTY.equals(desc[i][j]))
                        ) {

                    equal++;
                }
            }
            if (equal != desc.length - 1) {
                equal = 0;
            } else {
                return true;
            }
        }
        return equal == (desc.length - 1);
    }

    /**
     * Validate winner by horizontal.
     *
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    private boolean validateHorizontal(Cell[][] desc) {
        byte equal = 0;
        for (int j = 0; j != desc.length; j++) {
            for (int i = 0; i != desc.length - 1; i++) {
                if (desc[i][j].equals(desc[i + 1][j]) &&
                        !Cell.EMPTY.equals(desc[i][j])
                        ) {

                    equal++;
                }
            }

            if (equal != desc.length - 1) {
                equal = 0;
            } else {
                return true;
            }
        }

        return equal == (desc.length - 1);
    }

    /**
     * Validate winner by diagonals.
     *
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    private boolean validateDiagonals(Cell[][] desc) {
        byte equal = 0;
        for (int i = 0; i != desc.length - 1; i++) {
            if (desc[i][i].equals(desc[i + 1][i + 1]) &&
                    !Cell.EMPTY.equals(desc[i][i])
                    ) {

                equal++;
            }
        }
        if (equal == desc.length - 1) {
            return true;
        }

        equal = 0;
        for (int i = 0, j = (desc.length - 1);
             i != desc.length - 1 && j > 0; i++, j--) {

            if (desc[i][j].equals(desc[i + 1][j - 1]) &&
                    !Cell.EMPTY.equals(desc[i][j])
                    ) {

                equal++;
            }
        }

        return equal == (desc.length - 1);
    }
}