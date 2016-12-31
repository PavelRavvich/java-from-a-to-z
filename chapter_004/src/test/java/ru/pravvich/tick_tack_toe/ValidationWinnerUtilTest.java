package ru.pravvich.tick_tack_toe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidationWinnerUtilTest {

    @Test
    public void whenThen() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[0][0] = 'X';
        desc[1][0] = 'O';
        desc[2][0] = 'X';
        desc[0][1] = 'X';
        desc[1][1] = 'O';


        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.winnerDetermines(desc);

        assertThat(result, is(false));
    }

    /**
     * @see ValidationWinnerUtil#winnerDetermines(char[][])
     */
    @Test
    public void whenWinVerticalThenResultTrue() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[1][0] = 'X';
        desc[1][1] = 'X';
        desc[1][2] = 'X';

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.winnerDetermines(desc);

        assertThat(result, is(true));
    }

    /**
     * @see ValidationWinnerUtil#winnerDetermines(char[][])
     */
    @Test
    public void whenWinHorizontalThenResultTrue() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[0][2] = 'X';
        desc[1][2] = 'X';
        desc[2][2] = 'X';

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.winnerDetermines(desc);

        assertThat(result, is(true));
    }

    /**
     * @see ValidationWinnerUtil#winnerDetermines(char[][])
     */
    @Test
    public void whenWinDiagonalLeftUpToRightDownThenResultTrue() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[0][0] = 'X';
        desc[1][1] = 'X';
        desc[2][2] = 'X';

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.winnerDetermines(desc);

        assertThat(result, is(true));
    }

    /**
     * @see ValidationWinnerUtil#winnerDetermines(char[][])
     */
    @Test
    public void whenWinDiagonalRightUoToLeftDownThenResultTrue() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[2][0] = 'X';
        desc[1][1] = 'X';
        desc[0][2] = 'X';

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.winnerDetermines(desc);

        assertThat(result, is(true));
    }

    /**
     * @see ValidationWinnerUtil#winnerDetermines(char[][])
     */
    @Test
    public void thenNobodyWinnerThenReturnFalse() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[2][0] = 'X';
        desc[1][1] = 'X';
        desc[0][2] = 'O';

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.winnerDetermines(desc);

        assertThat(result, is(false));
    }

    /**
     * @see ValidationWinnerUtil#emptyCallExist(char[][])
     */
    @Test
    public void whenEmptyCellExistThenReturnTrue() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = ' ';
            }
        }

        desc[2][0] = 'X';
        desc[1][1] = 'X';
        desc[0][2] = 'O';

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.emptyCallExist(desc);

        assertThat(result, is(true));
    }

    /**
     * @see ValidationWinnerUtil#emptyCallExist(char[][])
     */
    @Test
    public void whenEmptyCellNotExistThenReturnFalse() {
        char[][] desc = new char[3][3];
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = 'X';
            }
        }

        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        boolean result = valid.emptyCallExist(desc);

        assertThat(result, is(false));
    }
}