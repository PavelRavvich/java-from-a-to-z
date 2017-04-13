package bomberman.units;

import bomberman.gameBoard.Board;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 11.04.17.
 *
 */
public class Gamer implements Walker {
    final AtomicReference<Board> board;
    private final AtomicInteger currentX;
    private final AtomicInteger currentY;
    final AtomicBoolean unitIsLive;

    public Gamer(final AtomicReference<Board> board,
                 final AtomicInteger startX,
                 final AtomicInteger startY) {

        this.unitIsLive = new AtomicBoolean(true);
        this.currentY = startY;
        this.currentX = startX;
        this.board = board;
    }

    @Override
    public void run() {
        while (this.unitIsLive.get() && !Thread.currentThread().isInterrupted()) {
            System.out.println("Game is start!");
            // TODO: 12.04.17 move gamer by inner data
        }
    }

    @Override
    public void die() {
        this.unitIsLive.set(false);
    }

    @Override
    public boolean moveUp() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();

        if (this.cellNotExists(x, y - 1)) return false;
        if (this.pointNotClear(x, y - 1)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[y - 1][x] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(y - 1);
        return false;
    }

    @Override
    public boolean moveDown() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();

        if (this.cellNotExists(x, y + 1)) return false;
        if (this.pointNotClear(x, y + 1)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[y + 1][x] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(y + 1);
        return false;
    }

    @Override
    public boolean moveRight() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();

        if (this.cellNotExists(x + 1, y)) return false;
        if (this.pointNotClear(x + 1, y)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[y][x + 1] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(x + 1);
        return true;
    }

    @Override
    public boolean moveLeft() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();

        if (this.cellNotExists(x - 1, y)) return false;
        if (this.pointNotClear(x - 1, y)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[y][x - 1] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(x - 1);
        return true;
    }


    protected boolean pointNotClear(final int futureX, final int futureY) {
        return this.board.get().getBoard()[futureY][futureX] == null;
    }

    private boolean cellNotExists(final int futureX, final int futureY) {
        final boolean cellIsExist =
                    futureX > (-1) &&
                    futureY > (-1) &&
                    futureX < board.get().getBoard()[0].length &&
                    futureY < board.get().getBoard().length;

        return !cellIsExist;
    }
}
