package bomberman.units;

import bomberman.gameBoard.Board;
import bomberman.gameBoard.GameBoard;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

public class MonsterTest {
    @Test
    public void whenMonsterMoveRightAndWayIsFreeThenMonsterExistInTargetCell() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final Walker unit = new Monster(board, new AtomicInteger(0), new AtomicInteger(0), cb);

        unit.moveRight();
        final Unit result = board.get().getBoard()[0][1];

        Assert.assertThat(result, is(unit));
    }

    @Test
    public void whenMonsterMoveRightAndWayIsFreeThenOldPositionIsNull() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final Walker unit = new Monster(board, new AtomicInteger(0), new AtomicInteger(0), cb);

        unit.moveRight();

        final Unit result = board.get().getBoard()[0][0];

        Assert.assertNull(result);
    }

    @Test
    public void whenMonsterMoveRightAndWayIsFreeThenMoveRightReturnTrue() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final Walker unit = new Monster(board, new AtomicInteger(0), new AtomicInteger(0), cb);

        final boolean result = unit.moveRight();

        Assert.assertTrue(result);
    }

    @Test
    public void whenMonsterMoveRightAndWayBusyThenSleep5SecAndReturnFalse() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final Walker unit1 = new Monster(board, new AtomicInteger(0), new AtomicInteger(0), cb);
        final Walker barrier = new Monster(board, new AtomicInteger(0), new AtomicInteger(1), cb);

        final boolean result = unit1.moveRight();
        Assert.assertFalse(result);
    }

    @Test
    public void when() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final Walker unit = new Monster(board, new AtomicInteger(0), new AtomicInteger(9), cb);

        final boolean result = unit.moveRight();

        Assert.assertFalse(result);
    }
}