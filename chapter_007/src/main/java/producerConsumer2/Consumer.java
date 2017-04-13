package producerConsumer2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Consumer {
    private final LinkedBlockingQueue<Integer> queue;
    private final ExecutorService consumers;
    private final AtomicBoolean stop;

    Consumer(final LinkedBlockingQueue<Integer> queue, AtomicBoolean stop) {
        this.consumers = Executors.newFixedThreadPool(3);
        this.queue = queue;
        this.stop = stop;
    }

    void consuming() {
        for (int i = 0; i < 3; i++) {
            final Thread consumer = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!stop.get()) {
                        try {
                            final Integer poll = queue.poll(100, TimeUnit.MILLISECONDS);
                            final Integer result = poll * 2;
                            //или что-нибудь поинтерснее...
                            System.out.println(result);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            this.consumers.submit(consumer);
        }
    }
}
