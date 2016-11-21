package ru.pravvich;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class OfficerTest {

    @Test
    public void whenRoadGoodThenOfficerMove() {

        Board.desc[4][4] = new Officer(new Cell(4,4),"white");
        Board.desc[4][4].move(new Cell(7,7));
        assertNull(Board.desc[4][4]);
        assertThat(Board.desc[7][7], instanceOf(Officer.class));
    }

    @Test
    public void whenRoadGoodThenOfficerMoveAndKill() {
        Board.desc[0][0] = new Officer(new Cell(0,0),"white");
        Board.desc[3][3] = new Officer(new Cell(3,3),"black");
        Board.desc[0][0].move(new Cell(3,3));
        assertNull(Board.desc[0][0]);
        assertThat(Board.desc[3][3].color, is("white"));
    }

    @Test
    public void whenInvalidValueCellThenOfficerThrowException() {
        Cell cell = new Cell(2,5);
        Board.desc[2][5] = new Officer(cell,"white");
        Board.desc[2][5].move(new Cell(3,7));
        assertThat(Board.desc[2][5].position,is(cell));
    }

}
