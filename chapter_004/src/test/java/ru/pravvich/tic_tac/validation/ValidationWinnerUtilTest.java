package ru.pravvich.tic_tac.validation;

import org.junit.Test;
import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.board.Board;
import ru.pravvich.tic_tac.board.Desk;
import ru.pravvich.tic_tac.validation.ValidationWinnerUtil;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidationWinnerUtilTest {
    @Test
    public void whenDescContainEmptyCellThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        boolean result = valid.emptyCellExist(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenDescNotContainEmptyCellThenReturnFalse() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.getBoard()[i][j] = Cell.X;
            }
        }
        boolean result = valid.emptyCellExist(board.getBoard());
        assertThat(result, is(false));

    }

    @Test
    public void whenWinnerDeterminesByHorizontal_0_ThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][0] = Cell.X;
        board.getBoard()[1][0] = Cell.X;
        board.getBoard()[2][0] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerDeterminesByHorizontal_1_ThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][1] = Cell.X;
        board.getBoard()[1][1] = Cell.X;
        board.getBoard()[2][1] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerDeterminesByHorizontal_2_ThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][2] = Cell.X;
        board.getBoard()[1][2] = Cell.X;
        board.getBoard()[2][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerNotDeterminesThenReturnFalse() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][2] = Cell.X;
        board.getBoard()[1][2] = Cell.X;
        board.getBoard()[2][2] = Cell.O;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(false));
    }

    @Test
    public void whenWinnerDeterminesByVertical_0_ThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][0] = Cell.X;
        board.getBoard()[0][1] = Cell.X;
        board.getBoard()[0][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerNotDeterminesByVertical_0_ThenReturnFalse() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][0] = Cell.X;
        board.getBoard()[0][1] = Cell.X;
        board.getBoard()[0][2] = Cell.O;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(false));
    }

    @Test
    public void whenWinnerDeterminesByVertical_1_ThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[1][0] = Cell.X;
        board.getBoard()[1][1] = Cell.X;
        board.getBoard()[1][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerNotDeterminesByVertical_1_ThenReturnFalse() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[1][0] = Cell.X;
        board.getBoard()[1][1] = Cell.O;
        board.getBoard()[1][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(false));
    }

    @Test
    public void whenWinnerDeterminesByVertical_2_ThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[2][0] = Cell.X;
        board.getBoard()[2][1] = Cell.X;
        board.getBoard()[2][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerNotDeterminesByVertical_2_ThenReturnFalse() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[2][0] = Cell.EMPTY;
        board.getBoard()[2][1] = Cell.X;
        board.getBoard()[2][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(false));
    }

    @Test
    public void whenWinnerDeterminesByDiagonalLeftUpToRightDownThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][0] = Cell.X;
        board.getBoard()[1][1] = Cell.X;
        board.getBoard()[2][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerNotDeterminesByDiagonalLeftUpToRightDownThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[0][0] = Cell.X;
        board.getBoard()[1][1] = Cell.O;
        board.getBoard()[2][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(false));
    }

    @Test
    public void whenWinnerDeterminesByDiagonalRightUpToLeftDownThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[2][0] = Cell.X;
        board.getBoard()[1][1] = Cell.X;
        board.getBoard()[0][2] = Cell.X;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(true));
    }

    @Test
    public void whenWinnerNotDeterminesByDiagonalRightUpToLeftDownThenReturnTrue() {
        ValidationWinnerUtil valid = new ValidationWinnerUtil();
        Desk board = new Board(3);
        board.getBoard()[2][0] = Cell.X;
        board.getBoard()[1][1] = Cell.X;
        board.getBoard()[0][2] = Cell.EMPTY;

        boolean result = valid.winnerDetermines(board.getBoard());
        assertThat(result, is(false));
    }
}