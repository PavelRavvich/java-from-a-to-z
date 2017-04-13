package multithredCounter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter04 {
    private AtomicInteger counter = new AtomicInteger(0);

    int start() {
        Thread t1 = getParallelThread();
        Thread t2 = getParallelThread();

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.counter.get();
    }

    private Thread getParallelThread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
    }

    private void increment() {
        for (int i = 0; i < 1_000_000; i++) {
            Counter04.this.counter.incrementAndGet();
        }
    }
}
