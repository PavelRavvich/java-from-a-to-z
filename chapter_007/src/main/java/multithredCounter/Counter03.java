package multithredCounter;

public class Counter03 {
    private int counter;

    int start() {
        Thread t1 = getParallelTread();
        Thread t2 = getParallelTread();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return counter;
    }

    private Thread getParallelTread() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
    }

    private synchronized void increment() {
        for (int i = 0; i < 1_000_000; i++) {
            Counter03.this.counter++;
        }
    }
}
