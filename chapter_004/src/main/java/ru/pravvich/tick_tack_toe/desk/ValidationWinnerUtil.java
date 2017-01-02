package ru.pravvich.tick_tack_toe.desk;

/**
 * Util class determines validates statements desc.
 */
public class ValidationWinnerUtil {

    /**
     * Check conditions for game can go on.
     * @return true if winner do not determines and empty cell is exist.
     */
    public boolean gameCanGoOn(char[][] desc) {
        return !this.winnerDetermines(desc) &&
                this.emptyCellExist(  desc);
    }

    /**
     * Check exist empty cell.
     * @param desc - desc for check.
     * @return true if we have empty cell. False if not empty cell.
     */
    public boolean emptyCellExist(char[][] desc) {
        for (char[] aDesc : desc) {
            for (int j = 0; j < desc.length; j++) {
                if (' ' == (aDesc[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines winner
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    public boolean winnerDetermines(char[][] desc) {
        return this.validateVertical(   desc) ||
                this.validateHorizontal(desc) ||
                this.validateDiagonals( desc);
    }

    /**
     * Validate winner by vertical.
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    private boolean validateVertical(char[][] desc) {
        byte equal = 0;
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != (desc.length - 1); j++) {
                if (desc[i][j] == (desc[i][j + 1]) &&
                        (' ' != (desc[i][j]))
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
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    private boolean validateHorizontal(char[][] desc) {
        byte equal = 0;
        for (int j = 0; j != desc.length; j++) {
            for (int i = 0; i != desc.length - 1; i++) {
                if (desc[i][j] == desc[i + 1][j] &&
                        (' ') != (desc[i][j])
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
     * @param desc - desc for check.
     * @return true if we have winner now. False if winner do not exist now.
     */
    private boolean validateDiagonals(char[][] desc) {
        byte equal = 0;
        for (int i = 0; i != desc.length - 1; i++) {
            if (desc[i][i] == (desc[i + 1][i + 1]) &&
                    (' ') != (desc[i][i])
                    ) {

                equal++;
            }
        }
        if (equal == desc.length - 1) {
            return true;
        }

        equal = 0;
        for (int i = 0, j = (desc.length - 1);
             i != desc.length - 1 && j > 0 ; i++, j--) {

            if (desc[i][j] == (desc[i + 1][j - 1]) &&
                    (' ') != (desc[i][j])
                    ) {

                equal++;
            }
        }

        return equal == (desc.length - 1);
    }
}