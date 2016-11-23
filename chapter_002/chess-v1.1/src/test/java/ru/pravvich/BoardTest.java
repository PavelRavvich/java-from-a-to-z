package ru.pravvich;

import org.junit.Test;
import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;
import ru.pravvich.figures.Figure;
import ru.pravvich.figures.Officer;
import ru.pravvich.figures.Place;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenFigureMoveThenNewPositionEqualsOldPositionAndOldPositionInstanceOfPlace() throws ImposableMoveException {
        for (int i = 0; i != Board.desc.length; i++) {
            Board.desc[i][i] = new Place(new Cell(i, i), "");
        }
        Board board = new Board();
        Figure officer = new Officer(new Cell(0, 0),"white");
        Board.desc[0][0] = officer;
        Cell cell = new Cell(5, 5);
        board.move(new Cell(0, 0), cell);
        assertThat(Board.desc[5][5],instanceOf(Officer.class));
        assertThat(Board.desc[5][5].getColor(),is("white"));
        assertThat(Board.desc[0][0],instanceOf(Place.class));
    }
}
