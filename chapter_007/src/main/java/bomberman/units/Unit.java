package bomberman.units;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by pavel on 11.04.17.
 *
 */
public interface Unit extends Runnable {
    /**
     * If call unit dies.
     */
    void die();
    /**
     * @return if unit die - true; if unit live - false.
     */
    AtomicBoolean isDie();
}
