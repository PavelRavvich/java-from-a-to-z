package problemsMultithreding;

class Problems {
    private int count = 0;

    int demo() {
        Thread thread01 = new Thread(new MyThread());
        Thread thread02 = new Thread(new MyThread());

        thread01.start();
        thread02.start();

        try {
            thread01.join();
            thread02.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.count;
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                count++;
            }
        }
    }
}
