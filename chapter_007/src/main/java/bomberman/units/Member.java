package bomberman.units;

import bomberman.gameBoard.Board;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 17.04.17.
 * Determines base form of Unit.
 */
abstract class Member implements Walker {
    /**
     * Current position by 'Y' on board.
     */
    private final AtomicInteger currentY;
    /**
     * Current position by 'X' on board.
     */
    private final AtomicInteger currentX;
    /**
     * Game's board.
     */
    final AtomicReference<Board> board;
    /**
     * Flag which determines live member or die.
     */
    final AtomicBoolean unitIsLive;

    /**
     * Default constructor.
     * @param board game's board.
     * @param startY first position on board by 'Y'.
     * @param startX first position on board by 'X'.
     */
    Member(final AtomicReference<Board> board,
           final AtomicInteger startY,
           final AtomicInteger startX) {

        this.unitIsLive = new AtomicBoolean(true);
        this.currentY = startY;
        this.currentX = startX;
        this.board = board;

        this.board.get().getBoard()[startY.get()][startX.get()] = this;
    }

    /**
     * All member of the board it's runnable objects.
     */
    @Override
    public abstract void run();

    /**
     * Kill unit.
     */
    @Override
    public void die() {
        this.unitIsLive.set(false);
        this.board
                .get()
                .getBoard()
                [this.currentY.get()][this.currentX.get()] = null;

        Thread.currentThread().interrupt();
    }

    /**
     * @return if unit die - true; if unit live - false.
     */
    @Override
    public AtomicBoolean isDie() {
        return this.unitIsLive;
    }

    /**
     * Moves unit up on one cell.
     * @return if successfully - true, else - false.
     */
    @Override
    public boolean moveUp() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();
        final int targetY = y - 1;

        if (this.cellNotExists(x, targetY)) return false;
        if (this.pointNotClear(x, targetY)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[targetY][x] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(targetY);
        return false;
    }

    /**
     * Moves unit down on one cell.
     * @return if successfully - true, else - false.
     */
    @Override
    public boolean moveDown() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();
        final int targetY = y + 1;

        if (this.cellNotExists(x, targetY)) return false;
        if (this.pointNotClear(x, targetY)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[targetY][x] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(targetY);
        return false;
    }

    /**
     * Moves unit right on one cell.
     * @return if successfully - true, else - false.
     */
    @Override
    public boolean moveRight() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();
        final int targetX = x + 1;

        if (this.cellNotExists(targetX, y)) return false;
        if (this.pointNotClear(targetX, y)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[y][targetX] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(targetX);
        return true;
    }

    /**
     * Moves unit left on one cell.
     * @return if successfully - true, else - false.
     */
    @Override
    public boolean moveLeft() {
        final int x = this.currentX.get();
        final int y = this.currentY.get();
        final int targetX = x - 1;

        if (this.cellNotExists(targetX, y)) return false;
        if (this.pointNotClear(targetX, y)) return false;

        final Unit unit = board.get().getBoard()[y][x];
        this.board.get().getBoard()[y][targetX] = unit;
        this.board.get().getBoard()[y][x] = null;
        this.currentX.set(targetX);
        return true;
    }

    /**
     * Check Is the cell occupied.
     * @param futureX position checking cell by X.
     * @param futureY position checking cell by Y.
     * @return if cell is NOT clear - true, if clear - false.
     */
    protected boolean pointNotClear(final int futureX, final int futureY) {
        return this.board.get().getBoard()[futureY][futureX] == null;
    }

    /**
     * Check exist cell.
     * @param futureX position checking cell by X.
     * @param futureY position checking cell by Y.
     * @return if cell is NOT exist - true, if exist - false.
     */
    private boolean cellNotExists(final int futureX, final int futureY) {
        final boolean cellIsExist =
                        futureX > (-1) &&
                        futureY > (-1) &&
                        futureX < this.board.get().getBoard()[0].length &&
                        futureY < this.board.get().getBoard().length;

        return !cellIsExist;
    }
}
