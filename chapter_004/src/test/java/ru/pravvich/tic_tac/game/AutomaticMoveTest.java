package ru.pravvich.tic_tac.game;

import org.junit.Test;
import ru.pravvich.tic_tac.*;
import ru.pravvich.tic_tac.board.*;
import ru.pravvich.tic_tac.location.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AutomaticMoveTest {

    @Test
    public void whenDeskEmptyThenGeneratePositionZeroY_ZeroX() throws IllegalMove {
        AutomaticMove automaticMove = new AutomaticMove();
        Desk board = new Board(3);
        Location resultPos = automaticMove.generateMove(board);

        assertThat(resultPos.getY(), is(0));
        assertThat(resultPos.getX(), is(0));
    }

    @Test
    public void whenPosition_ZeroY_ZeroX_IsBusyThenGeneratePositionZeroY_OneX() throws IllegalMove {
        AutomaticMove automaticMove = new AutomaticMove();
        Location pos = new Position(0, 0);
        Desk board = new Board(3);
        board.move(Cell.O, pos);
        Location resultPos = automaticMove.generateMove(board);

        assertThat(resultPos.getY(), is(0));
        assertThat(resultPos.getX(), is(1));
    }

    @Test
    public void whenPosition_ZeroY_ZeroX_AndZeroY_OneX_IsBusyThenGeneratePositionZeroY_TwoX() throws IllegalMove {
        AutomaticMove automaticMove = new AutomaticMove();
        Location pos = new Position(0, 0);
        Location pos1 = new Position(1, 0);
        Desk board = new Board(3);
        board.move(Cell.O, pos);
        board.move(Cell.O, pos1);

        Location resultPos = automaticMove.generateMove(board);
        assertThat(resultPos.getY(), is(0));
        assertThat(resultPos.getX(), is(2));
    }

    @Test
    public void whenFirstLineBusyThenGeneratePositionOneY_ZeroX() throws IllegalMove {
        AutomaticMove automaticMove = new AutomaticMove();
        Location pos = new Position(0, 0);
        Location pos1 = new Position(1, 0);
        Location pos2 = new Position(2, 0);
        Desk board = new Board(3);
        board.move(Cell.O, pos);
        board.move(Cell.O, pos1);
        board.move(Cell.O, pos2);

        Location resultPos = automaticMove.generateMove(board);
        assertThat(resultPos.getY(), is(1));
        assertThat(resultPos.getX(), is(0));
    }

    @Test
    public void whenThenGeneratePositionZeroY_ZeroX() throws IllegalMove {
        AutomaticMove automaticMove = new AutomaticMove();
        Location pos = new Position(0, 1);
        Location pos1 = new Position(1, 1);
        Desk board = new Board(3);
        board.move(Cell.O, pos);
        board.move(Cell.O, pos1);
        Location resultPos = automaticMove.generateMove(board);

        assertThat(resultPos.getY(), is(0));
        assertThat(resultPos.getX(), is(0));
    }
}