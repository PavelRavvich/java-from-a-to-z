package bomberman.gameBoard;

import bomberman.units.Unit;

/**
 * Created by pavel on 11.04.17.
 */
public interface Board {
    Unit[][] getBoard();
    boolean setCell(final int x, final int y, final Unit unit);
}
