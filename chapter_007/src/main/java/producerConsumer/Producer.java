package producerConsumer;

import java.util.Arrays;
import java.util.Queue;

/**
 * Tread producer. Add data in shared queue.
 * @param <E> - any.
 */
public class Producer<E> implements Runnable {
    /**
     * Data roe add.
     */
    private E[] src;
    /**
     * Shared data-structure.
     */
    private final Queue<E> sharedQueue;

    /**
     * Default constructor.
     * @param sharedQueue data structure for shared.
     * @param src first ste data for add.
     */
    public Producer(final Queue sharedQueue, E[] src) {
        this.sharedQueue = sharedQueue;
        this.src = src;
    }

    /**
     * For load new data for add in queue.
     * @param src data for add.
     */
    public void setSrc(E[] src) {
        this.src = src;
    }

    /**
     * Produce first src array, and puts on hold more data.
     */
    @Override
    public void run() {
        E[] tempSrc = src;
        forEachProduce();
        nextSetProduce(tempSrc);

    }

    /**
     * Check has been added last src data.
     * If true - sleep 3 seconds, else add elements in queue.
     * And recursive call.
     * @param tempSrc reference on temp src object.
     */
    private void nextSetProduce(E[] tempSrc) {
        while (Arrays.equals(tempSrc, src)) {
            try {
                // тут мне на монитор влиять не надо так что sleep в самый раз.
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        forEachProduce();
        tempSrc = src;
        nextSetProduce(tempSrc);
    }

    /**
     * For each src array for add.
     */
    private void forEachProduce() {
        for (E e : src) {
            try {
                produce(e);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

    /**
     * If queue is full current thread wait and free lock object.
     * Else add element in queue, holds lock object.
     * @param element for add.
     * @throws InterruptedException from wait().
     */
    private void produce(E element) throws InterruptedException {
        //wait if queue is full
        while (sharedQueue.size() == 10) {
            synchronized (sharedQueue) {
                sharedQueue.wait();
            }
        }

        //producing element and notify consumers
        synchronized (sharedQueue) {
            sharedQueue.add(element);
            //System.out.println(sharedQueue.size());
            sharedQueue.notifyAll();
        }
    }
}
