package ru.pravvich;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.pravvich.figures.Figure;
import ru.pravvich.figures.Officer;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenFigureMoveThenNewPositionAndOldPositionChangeNull() throws ImposableMoveException {
        Board board = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j != 8; j++) {
                Board.desc[i][j] = null;
            }
        }
        Figure officer = new Officer(new Cell(0, 0), "white");
        Board.desc[0][0] = officer;
        Cell cell = new Cell(5, 5);
        board.move(Board.desc[0][0].getPosition(), cell);
        assertThat(Board.desc[5][5], instanceOf(Officer.class));
        assertThat(Board.desc[5][5].getColor(), is("white"));
        assertNull(Board.desc[0][0]);
    }

    @Test
    public void whenFigureKillThenTheKilledFigureDisappears() throws ImposableMoveException {
        Board board = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j != 8; j++) {
                Board.desc[i][j] = null;
            }
        }
        Cell cell = new Cell(5, 5);
        Board.desc[0][0] = new Officer(new Cell(0, 0), "white");
        Board.desc[5][5] = new Officer(new Cell(5, 5), "black");
        board.move(Board.desc[0][0].getPosition(), cell);
        assertThat(Board.desc[5][5].getColor(), is("white"));
        assertNull(Board.desc[0][0]);
    }

    @Test
    public void whenFigureNotExistThenThrowImposableMoveException() throws ImposableMoveException {
        Board board = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j != 8; j++) {
                Board.desc[i][j] = null;
            }
        }
        Cell cell = new Cell(5, 5);
        this.exception.expect(ImposableMoveException.class);
        this.exception.expectMessage(containsString("Error move"));
        board.move(new Cell(0, 0), cell);
    }

    @Test(expected = ImposableMoveException.class)
    public void whenRoadHaveBarrierThenThrowImposableMoveException() throws ImposableMoveException {
        Board board = new Board();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j != 8; j++) {
                Board.desc[i][j] = null;
            }
        }
        Cell cell = new Cell(5, 5);
        Board.desc[0][0] = new Officer(new Cell(0, 0), "white");
        Board.desc[4][4] = new Officer(new Cell(4, 4), "white");
        board.move(new Cell(0, 0), cell);
    }

    @Test(expected = ImposableMoveException.class)
    public void whenFriendFireThenThrowImposableMoveException() throws ImposableMoveException {
        Board board = new Board();
        for (int i = 0; i < Board.desc.length; i++) {
            for (int j = 0; j < Board.desc[i].length; j++) {
                Board.desc[i][j] = null;
            }
        }
        Cell cell = new Cell(5, 5);
        Board.desc[0][0] = new Officer(new Cell(0, 0), "white");
        Board.desc[5][5] = new Officer(new Cell(5, 5), "white");
        board.move(new Cell(0, 0), cell);
    }
}