package ru.pravvich.figures;

import org.junit.*;
import ru.pravvich.Board;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.pravvich.Board.desc;
import static org.hamcrest.CoreMatchers.instanceOf;

public class PawnTest {
    @Test
    public void whenWhitePawnFirstMoveOnTwoCellThenMoveOnTwoCell() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        Pawn pawn = new Pawn("white", id, 1, 6);
        desc[1][6] = pawn;
        desc[1][6].move(new int[] {1, 4});
        assertThat(desc[1][4].getId(),is(id));
        assertThat(pawn.getCounterMoves(),is(2));
        assertThat(desc[1][6], instanceOf(Place.class));
    }

    @Test
    public void whenBlackPawnFirstMoveOnTwoCellThenMoveOnTwoCell() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        Pawn pawn = new Pawn("black", id, 7, 1);
        desc[7][1] = pawn;
        desc[7][1].move(new int[] {7, 3});
        assertThat(desc[7][3].getId(),is(id));
        assertThat(pawn.getCounterMoves(),is(2));
        assertThat(desc[7][1], instanceOf(Place.class));
    }

    @Test
    public void whenWhitePawnGoUpThenWorkSwapOnlyMoveUp() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        Pawn pawn = new Pawn("white", id, 2, 6);
        desc[2][6] = pawn;
        desc[2][6].move(new int[] {2, 5});
        assertThat(desc[2][5].getId(),is(id));
        assertThat(desc[2][6], instanceOf(Place.class));
    }

    @Test
    public void whenBlackPawnGoUpThenWorkSwapOnlyMoveUp() {
        Board board = new Board();
        for (int i = 0; i != 8; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[j][i] = new Place(j,i);
            }
        }
        int id = board.generateID();
        Pawn pawn = new Pawn("black", id, 3, 1);
        desc[3][1] = pawn;
        desc[3][1].move(new int[] {3, 2});
        assertThat(desc[3][2].getId(),is(id));
        assertThat(desc[3][1], instanceOf(Place.class));
    }

    @Test
    public void whenWhitePawnEatBlackFigureInRightThenBlackFigureGoInDeathFigure() {
        Board board = new Board();
        int id = board.generateID();
        desc[0][7] = new Pawn("white",id,0,7);
        desc[1][6] = new Pawn("black",board.generateID(),1,6);
        desc[0][7].move(new int[] {1, 6});
        assertThat(desc[1][6].getId(),is(id));
        assertThat(desc[0][7], instanceOf(Place.class));
    }

    @Test
    public void whenWhitePawnEatBlackFigureInLeftThenBlackFigureGoInDeathFigure() {
        Board board = new Board();
        int id = board.generateID();
        desc[1][1] = new Pawn("white",id,1,1);
        desc[0][0] = new Pawn("black",board.generateID(),0,0);
        desc[1][1].move(new int[] {0, 0});
        assertThat(desc[0][0].getId(),is(id));
        assertThat(desc[1][1], instanceOf(Place.class));
    }

    @Test
    public void whenBlackPawnEatWhiteFigureInRightThenWhiteFigureGoInDeathFigure() {
        Board board = new Board();
        int id = board.generateID();
        desc[0][0] = new Pawn("black",id,0,0);
        desc[1][1] = new Pawn("white",board.generateID(),1,1);
        desc[0][0].move(new int[] {1, 1});
        assertThat(desc[1][1].getId(),is(id));
        assertThat(desc[0][0], instanceOf(Place.class));
    }

    @Test
    public void whenBlackPawnEatWhiteFigureInLeftThenWhiteFigureGoInDeathFigure() {
        Board board = new Board();
        int id = board.generateID();
        desc[7][0] = new Pawn("black",id,7,0);
        desc[6][1] = new Pawn("white",board.generateID(),6,1);
        desc[7][0].move(new int[] {6, 1});
        assertThat(desc[6][1].getId(),is(id));
        assertThat(desc[7][0], instanceOf(Place.class));
    }
}