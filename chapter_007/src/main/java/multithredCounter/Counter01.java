package multithredCounter;

public class Counter01 {
    private transient int counter = 0;

    public int getCounter() {
        this.start();
        return counter;
    }

    private void start() {
        for (int i = 0; i < 2; i++) {
            Thread thread = increment();
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Thread increment() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    Counter01.this.counter++;
                }
            }
        });
    }
}
