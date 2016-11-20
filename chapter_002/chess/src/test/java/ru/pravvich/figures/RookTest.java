package ru.pravvich.figures;

import org.junit.*;
import ru.pravvich.Board;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.pravvich.Board.deathFigures;
import static ru.pravvich.Board.desc;
import static org.hamcrest.CoreMatchers.instanceOf;

public class RookTest {

    /**
     * @see Rook#moveUp(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoInNewPositionAndOldPositionInstancePlace() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,0,7);
        desc[0][7] = rook;
        rook.move(new int[]{0,2});
        assertThat(desc[0][2].getId(),is(id));
        assertThat(desc[0][7], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveUp(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoInNewPositionAndOldPositionInstancePlaceAndKillEnemy() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,0,7);
        desc[0][7] = rook;
        desc[0][2] = new Rook("black",board.generateID(),0,2);
        rook.move(new int[]{0,2});
        assertThat(desc[0][2].getId(),is(id));
        assertThat(desc[0][7], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveRight(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoRightAndOldPositionInstancePlace() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,0,7);
        desc[0][7] = rook;
        rook.move(new int[]{7,7});
        assertThat(desc[7][7].getId(),is(id));
        assertThat(desc[0][7], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveRight(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoRightAndOldPositionInstancePlaceAndKill() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,0,7);
        desc[0][7] = rook;
        desc[7][7] = new Rook("black",board.generateID(),7,7);
        rook.move(new int[]{7,7});
        assertThat(desc[7][7].getId(),is(id));
        assertThat(desc[0][7], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveLeft(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoLeftAndOldPositionInstancePlace() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,7,7);
        desc[7][7] = rook;
        rook.move(new int[]{0,7});
        assertThat(desc[0][7].getId(),is(id));
        assertThat(desc[7][7], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveLeft(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoLeftAndOldPositionInstancePlaceAndKillEnemy() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,7,7);
        desc[7][7] = rook;
        desc[0][7] = new Rook("black",board.generateID(),0,7);
        rook.move(new int[]{0,7});
        assertThat(desc[0][7].getId(),is(id));
        assertThat(desc[7][7], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveDown(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoDownAndOldPositionInstancePlace() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,7,0);
        desc[7][0] = rook;
        rook.move(new int[]{7,7});
        assertThat(desc[7][7].getId(),is(id));
        assertThat(desc[7][0], instanceOf(Place.class));
    }

    /**
     * @see Rook#moveDown(int[])
     */
    @Test
    public void whenNewPositionInThenRookGoDownAndKillAndOldPositionInstancePlace() {
        Board board = new Board();
        for (int i = 0; i != desc.length; i++) {
            for (int j = 0; j != desc.length; j++) {
                desc[i][j] = new Place(i,j);
            }
        }
        int id = board.generateID();
        Rook rook = new Rook("white",id,7,0);
        desc[7][0] = rook;
        Rook forDeath = new Rook("black",board.generateID(),7,7);
        desc[7][7] = forDeath;
        rook.move(new int[]{7,7});
        assertThat(desc[7][7].getId(),is(id));
        assertThat(desc[7][0], instanceOf(Place.class));
    }
}