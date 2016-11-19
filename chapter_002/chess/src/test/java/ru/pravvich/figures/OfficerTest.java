package ru.pravvich.figures;

import org.junit.*;
import ru.pravvich.Board;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.pravvich.Board.desc;

public class OfficerTest {
    @Test
    public void whenRoadIsEmptyThenFigureMoveUpRightAndOldLocationReplaceOnPlaceType() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        desc[2][7] = new Officer("white", id, 2, 7);
        desc[2][7].move(new int[] {4, 5});
        boolean res = (desc[2][7] instanceof Place);
        assertThat(desc[4][5].getId(),is(id));
        assertThat(res,is(true));
    }

    @Test
    public void whenMoveRightUpAndKillThenVictimReplaceOnOfficerAndBaseOfficerInstanceOfNewPlace() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int idFood = board.generateID();
        desc[4][5] = new Officer("black",idFood,4,5);
        int id = board.generateID();
        desc[2][7] = new Officer("white", id, 2, 7);
        desc[2][7].move(new int[] {4, 5});
        boolean result = (desc[2][7] instanceof Place);
        assertThat(result,is(true));
        assertThat(desc[4][5].getId(),is(id));
    }

    @Test
    public void whenRoadIsEmptyThenFigureMoveUpLeftAndOldLocationReplaceOnPlaceType() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        desc[2][7] = new Officer("white", id, 2, 7);
        desc[2][7].move(new int[] {0, 5});
        boolean res = (desc[2][7] instanceof Place);
        assertThat(desc[0][5].getId(),is(id));
        assertThat(res,is(true));
    }

    @Test
    public void whenMoveLeftUpAndKillThenVictimReplaceOnOfficerAndBaseOfficerInstanceOfNewPlace() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int idFood = board.generateID();
        desc[0][5] = new Officer("black",idFood,0,5);
        int id = board.generateID();
        desc[2][7] = new Officer("white", id, 2, 7);
        desc[2][7].move(new int[] {0, 5});
        boolean result = (desc[2][7] instanceof Place);
        assertThat(result,is(true));
        assertThat(desc[0][5].getId(),is(id));
    }

    @Test
    public void thenRoadIsEmptyThenFigureMoveDownRightAndOldLocationReplaceOnPlaceType() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        desc[2][2] = new Officer("white", id, 2, 2);
        desc[2][2].move(new int[] {3, 3});
        boolean res = (desc[2][2] instanceof Place);
        assertThat(res,is(true));
        assertThat(desc[3][3].getId(),is(id));
    }

    @Test
    public void thenMoveRightDownAndKillThenVictimReplaceOnOfficerAndBaseOfficerInstanceOfNewPlace() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int idFood = board.generateID();
        desc[7][7] = new Officer("black",idFood,7,7);
        int id = board.generateID();
        desc[0][0] = new Officer("white", id, 0, 0);
        desc[0][0].move(new int[] {7, 7});
        boolean result = (desc[0][0] instanceof Place);
        assertThat(result,is(true));
        assertThat(desc[7][7].getId(),is(id));
    }

    @Test
    public void thenRoadIsEmptyThenFigureMoveDownLeftAndOldLocationReplaceOnPlaceType() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        desc[7][0] = new Officer("white", id, 7, 0);
        desc[7][0].move(new int[] {3, 4});
        boolean res = (desc[7][0] instanceof Place);
        assertThat(res,is(true));
        assertThat(desc[3][4].getId(),is(id));
    }

    @Test
    public void thenMoveLeftDownAndKillThenVictimReplaceOnOfficerAndBaseOfficerInstanceOfNewPlace() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int idFood = board.generateID();
        desc[1][6] = new Officer("black",idFood,1,6);
        int id = board.generateID();
        desc[6][1] = new Officer("white", id, 6, 1);
        desc[6][1].move(new int[] {1, 6});
        boolean result = (desc[6][1] instanceof Place);
        assertThat(result,is(true));
        assertThat(desc[1][6].getId(),is(id));
    }
}
