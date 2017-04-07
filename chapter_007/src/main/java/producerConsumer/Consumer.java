package producerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Tread consumer. Extracts data from shared queue.
 * @param <E> - any.
 */
public class Consumer<E> implements Runnable {
    /**
     * Shared data-structure.
     */
    private final Queue<E> sharedQueue;
    /**
     * For save results which get from queue.
     */
    private List<E> result = new ArrayList<>();

    /**
     * Getter for result data.
     * @return result data.
     */
    public List<E> getResult() {
        return result;
    }

    /**
     * Default constructor.
     * @param sharedQueue init shared queue for extract.
     */
    public Consumer(Queue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    /**
     * Get elements from queue and add in result list.
     */
    @Override
    public void run() {
        while (true) {
            try {
                result.add(consume());
                //System.out.println(sharedQueue.size());
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Get next element from queue.
     * If queue is empty call wait() and free lock object(shared queue).
     * @return Element if queue not empty.
     * @throws InterruptedException - from wait.
     */
    private E consume() throws InterruptedException {
        while (sharedQueue.isEmpty()) {
            synchronized (sharedQueue) {
                sharedQueue.wait();
            }
        }

        //If not empty consume element and notify waiting producer
        synchronized (sharedQueue) {
            sharedQueue.notifyAll();
            return sharedQueue.poll();
        }
    }
}
