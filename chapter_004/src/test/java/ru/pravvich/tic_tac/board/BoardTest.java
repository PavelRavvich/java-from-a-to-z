package ru.pravvich.tic_tac.board;

import org.junit.Test;
import ru.pravvich.tic_tac.Cell;
import ru.pravvich.tic_tac.IllegalMove;
import ru.pravvich.tic_tac.location.Position;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void whenSizeForBoardGoInConstructorThenBoardSizeEqualThisValue() {
        Desk board = new Board(3);
        assertThat(board.getBoard().length, is(3));
        assertThat(board.getBoard()[0].length, is(3));
    }

    @Test
    public void whenMoveWorkThenCellInitNewValue() throws IllegalMove {
        Desk board = new Board(3);
        board.move(Cell.X, new Position(0, 0));
        assertThat(board.getBoard()[0][0], is(Cell.X));
    }

    @Test(expected = IllegalMove.class)
    public void whenMoveWorkButCellOccupiedThenTrowIllegalMove() throws IllegalMove {
        Desk board = new Board(3);
        board.move(Cell.X, new Position(0, 0));
        board.move(Cell.X, new Position(0, 0));
    }
}