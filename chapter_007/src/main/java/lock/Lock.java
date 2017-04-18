package lock;

public class Lock {
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public void lock() throws InterruptedException {
        synchronized (this) {
            Thread callingThread = Thread.currentThread();
            while (this.lockedBy != callingThread) {
                wait();
            }

            this.lockedCount++;
            this.lockedBy = callingThread;
        }
    }

    public void unlock() {
        synchronized (this) {
            if (Thread.currentThread() == this.lockedBy) {
                this.lockedCount--;

                if (this.lockedCount == 0) {
                    notify();
                }
            }
        }
    }
}
