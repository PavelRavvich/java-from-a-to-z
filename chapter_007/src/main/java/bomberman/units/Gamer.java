package bomberman.units;

import bomberman.gameBoard.Board;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 11.04.17.
 * Determines User's behavior.
 */
public class Gamer extends Member implements User {
    /**
     * Default constructor.
     * @param board link on games's board.
     * @param startX start position by X.
     * @param startY start position by Y.
     */
    public Gamer(final AtomicReference<Board> board,
                 final AtomicInteger startX,
                 final AtomicInteger startY) {

        super(board, startY, startX);
    }

    @Override
    public void run() {
        while (this.unitIsLive.get() && !Thread.currentThread().isInterrupted()) {
            System.out.println("Game is start!");
            // TODO: 12.04.17 move gamer by inner data.
        }
    }

    /**
     *
     * @param y target by x.
     * @param x target by y.
     * @return if got - true, else - false.
     */
    @Override
    public boolean shoot(final int y, final int x) {
        if (this.board.get().getBoard()[y][x] != null) {
            this.board.get().getBoard()[y][x].die();
            return true;
        }
        return false;
    }
}
