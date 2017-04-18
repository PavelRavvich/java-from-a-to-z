package bomberman.units;

/**
 * Created by pavel on 11.04.17.
 *
 */
public interface Walker extends Unit {
    /**
     * Moves unit up on one cell.
     * @return if successfully - true, else - false.
     */
    boolean moveUp();
    /**
     * Moves unit down on one cell.
     * @return if successfully - true, else - false.
     */
    boolean moveDown();
    /**
     * Moves unit right on one cell.
     * @return if successfully - true, else - false.
     */
    boolean moveRight();
    /**
     * Moves unit left on one cell.
     * @return if successfully - true, else - false.
     */
    boolean moveLeft();
}
