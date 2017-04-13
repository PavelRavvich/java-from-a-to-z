package bomberman.units;

/**
 * Created by pavel on 11.04.17.
 *
 */
public interface Walker extends Unit {
    boolean moveUp();
    boolean moveDown();
    boolean moveRight();
    boolean moveLeft();

}
