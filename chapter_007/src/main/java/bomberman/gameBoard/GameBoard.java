package bomberman.gameBoard;

import bomberman.units.Unit;

/**
 * Created by pavel on 11.04.17.
 *
 */
public class GameBoard implements Board {

    private final Unit[][] board;

    public GameBoard(final int height, final int width) {
        this.board = new Unit[height][width];
    }

    @Override
    public Unit[][] getBoard() {
        return this.board;
    }

    public boolean setCell(final int x, final int y, final Unit unit) {
        if (this.board[y][x] != null) return false;
        this.board[y][x] = unit;
        return true;
    }
}
