package bomberman.gameBoard;

import bomberman.units.Unit;

/**
 * Created by pavel on 11.04.17.
 * Board for game 2D.
 */
public class GameBoard implements Board {
    /**
     * Double array of Unit.
     * All cells which will not have monster or user equals null.
     */
    private final Unit[][] board;

    /**
     * Default constructor.
     * @param height is 'Y'.
     * @param width is 'X'.
     */
    public GameBoard(final int height, final int width) {
        this.board = new Unit[height][width];
    }

    /**
     * Getter for board array.
     * @return board array.
     */
    @Override
    public Unit[][] getBoard() {
        return this.board;
    }
}
