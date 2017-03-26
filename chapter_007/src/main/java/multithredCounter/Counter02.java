package multithredCounter;

public class Counter02 {
    private int counter;
    private final Object monitor;

    Counter02(final Object monitor, int counter) {
        this.monitor = monitor;
        this.counter = counter;
    }

    public int start() {
        for (int i = 0; i < 2; i++) {
            Thread thread = getParallelTread();
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.counter;
    }

    private Thread getParallelTread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Counter02.this.increment();
            }
        });
    }

    private void increment() {
        // critical section.
        synchronized (this.monitor) {
            for (int i = 0; i < 1_000_000; i++) {
                this.counter++;
            }
        }
    }
}
