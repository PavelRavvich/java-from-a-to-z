package ru.pravvich.figures;

import org.junit.Test;
import ru.pravvich.Board;
import ru.pravvich.Cell;
import ru.pravvich.ImposableMoveException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OfficerTest {

    @Test
    public void whenRoadGoodThenReturnCellArrWithRoad() throws ImposableMoveException {
        for (int i = 0; i != 8 ; i++) {
            Board.desc[i][i] = new Place(new Cell(i,i),"");
        }
        Board.desc[4][4] = new Officer(new Cell(4,4),"white");
        Cell[] result = Board.desc[4][4].move(new Cell(7,7));
        Cell[] check = new Cell[2];
        check[0] = new Cell(5,5);
        check[1] = new Cell(6,6);
        assertThat(check[0].getX(), is(result[0].getX()));
        assertThat(check[0].getY(), is(result[0].getY()));
        assertThat(check[1].getX(), is(result[1].getX()));
        assertThat(check[1].getY(), is(result[1].getY()));
        assertThat(check.length, is(result.length));
    }

    @Test
    public void whenRoadBadThenThrowImposableMoveException() throws ImposableMoveException {
        for (int i = 0; i != 8 ; i++) {
            Board.desc[i][i] = new Place(new Cell(i,i),"");
        }
        Board.desc[4][4] = new Officer(new Cell(4,4),"white");
        Board.desc[6][6] = new Officer(new Cell(6,6),"white");
        Cell[] result = Board.desc[4][4].move(new Cell(7,7));
        assertThat(result.length, is(0));
    }
}