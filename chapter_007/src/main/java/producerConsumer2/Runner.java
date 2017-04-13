package producerConsumer2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Runner {
    final private AtomicBoolean stop;
    final private LinkedBlockingQueue<Integer> queue;

    Runner() {
        this.stop = new AtomicBoolean(false);
        this.queue = new LinkedBlockingQueue<>();
    }


    public void start() {
        final Consumer consumer = new Consumer(queue, stop);
        final Producer producer = new Producer(queue, stop);
        producer.producing();
        consumer.consuming();
        final Timer timer = new Timer();
        timer.schedule(new Stopper(), 10000);
    }

    private class Stopper extends TimerTask {
        @Override
        public void run() {
            stop.set(true);
        }
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.start();
    }
}
