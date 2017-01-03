package ru.pravvich.tick_tack_toe.desk;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for validations statements desc.
 */
public class ValidationWinnerUtilTest {

    /**
     * When winner not determines and desc have empty cells.
     * @see ValidationWinnerUtil#gameCanGoOn(char[][]).
     */
    @Test
    public void whenWinnerNotDeterminesAndEmptyCellExistThenReturnTrue() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = ' ';
            }
        }

        boolean result = valid.gameCanGoOn(desc);
        assertThat(result, is(true));
    }

    /**
     * When empty cell exist.
     *
     * @see ValidationWinnerUtil#emptyCellExist(char[][]).
     */
    @Test
    public void whenEmptyCellExistThenReturnTrue() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = ' ';
            }
        }

        boolean result = valid.emptyCellExist(desc);
        assertThat(result, is(true));
    }

    /**
     * When empty cell not exist.
     *
     * @see ValidationWinnerUtil#emptyCellExist(char[][]).
     */
    @Test
    public void whenEmptyCellNotExistThenReturnFalse() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = 'X';
            }
        }

        boolean result = valid.emptyCellExist(desc);
        assertThat(result, is(false));
    }

    /**
     * When winner determines by horizontal.
     *
     * @see ValidationWinnerUtil#emptyCellExist(char[][]).
     */
    @Test
    public void whenWinnerDeterminesByHorizontalThenReturnTrue() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = ' ';
            }
        }
        desc[1][0] = 'X';
        desc[1][1] = 'X';
        desc[1][2] = 'X';

        boolean result = valid.winnerDetermines(desc);
        assertThat(result, is(true));
    }

    /**
     * When winner determines by horizontal.
     *
     * @see ValidationWinnerUtil#emptyCellExist(char[][])
     */
    @Test
    public void whenWinnerDeterminesByVerticalThenReturnTrue() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = ' ';
            }
        }
        desc[0][1] = 'X';
        desc[1][1] = 'X';
        desc[2][1] = 'X';

        boolean result = valid.winnerDetermines(desc);
        assertThat(result, is(true));
    }

    /**
     * When winner determines by diagonal up-left to right-down.
     *
     * @see ValidationWinnerUtil#winnerDetermines(char[][]).
     */
    @Test
    public void whenWinnerDeterminesByDiagonalUpLeftToRightDownThenReturnTrue() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = ' ';
            }
        }
        desc[0][0] = 'X';
        desc[1][1] = 'X';
        desc[2][2] = 'X';

        boolean result = valid.winnerDetermines(desc);
        assertThat(result, is(true));
    }

    /**
     * When winner determines by diagonal up-right to left-down.
     *
     * @see ValidationWinnerUtil#winnerDetermines(char[][]).
     */
    @Test
    public void whenWinnerDeterminesByDiagonalUpRightToLeftDownThenReturnTrue() {
        Validation valid = new ValidationWinnerUtil();
        char[][] desc = new char[3][3];
        for (int i = 0; i < desc.length; i++) {
            for (int j = 0; j < desc.length; j++) {
                desc[i][j] = ' ';
            }
        }
        desc[2][0] = 'X';
        desc[1][1] = 'X';
        desc[0][2] = 'X';

        boolean result = valid.winnerDetermines(desc);
        assertThat(result, is(true));
    }
}