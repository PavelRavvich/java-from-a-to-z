package bomberman.units;

import bomberman.gameBoard.Board;
import bomberman.gameBoard.GameBoard;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

public class GamerTest {
    @Test
    public void whenUserShootInMonsterAndGotMonsterThenCellOfMonsterEqualsNull() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final User user = new Gamer(board, new AtomicInteger(0), new AtomicInteger(0));
        new Monster(board, new AtomicInteger(0), new AtomicInteger(1), cb);

        user.shoot(0, 1);

        final Unit result = board.get().getBoard()[0][1];
        Assert.assertNull(result);
    }

    @Test
    public void whenUserShootInMonsterAndGotMonsterThenReturnTrue() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final User user = new Gamer(board, new AtomicInteger(0), new AtomicInteger(0));
        new Monster(board, new AtomicInteger(0), new AtomicInteger(1), cb);

        final boolean result = user.shoot(0, 1);
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenUserShootInMonsterAndMissedMonsterThenReturnFalse() {
        final CyclicBarrier cb = new CyclicBarrier(1);
        final AtomicReference<Board> board = new AtomicReference<>(new GameBoard(10, 10));
        final User user = new Gamer(board, new AtomicInteger(0), new AtomicInteger(0));

        final boolean result = user.shoot(0, 1);
        Assert.assertThat(result, is(false));
    }
}