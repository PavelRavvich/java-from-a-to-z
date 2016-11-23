package ru.pravvich.figures;

import org.junit.Test;
import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OfficerTest {
    @Test
    public void whenFigureMoveNotDiagonalThenNothingOccurs() throws ImposableMoveException {
        Figure[][] desc = new Figure[8][8];
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(new Cell(i, i), "");
            }
        }

        desc[4][4] = new Officer(new Cell(4, 4), "white");
        desc[4][4].move(new Cell(5, 7));
        assertThat(desc[4][4].getColor(), is("white"));
        assertThat(desc[4][7], instanceOf(Place.class));
    }

    @Test
    public void whenThenReturnArrayCellRoadWithoutFinishCell() throws ImposableMoveException {
        Figure[][] desc = new Figure[8][8];
        for (int i = 0; i != 8; i++) {
            desc[i][i] = new Place(new Cell(i, i), "");
        }
        desc[4][4] = new Officer(new Cell(4, 4), "white");
        Cell[] result = desc[4][4].move(new Cell(7, 7));
        assertThat(result.length, is(2));
    }

    @Test
    public void whenRoadNotImposableForOfficerThenThrowImposableMoveException() throws ImposableMoveException {
        Figure[][] desc = new Figure[8][8];
        for (int i = 0; i != 8; i++) {
            desc[i][i] = new Place(new Cell(i, i), "");
        }
        desc[4][4] = new Officer(new Cell(4, 4), "white");
        Cell[] result = desc[4][4].move(new Cell(6, 7));
        assertThat(result.length, is(0));
    }
}