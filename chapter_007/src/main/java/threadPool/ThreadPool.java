package threadPool;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPool implements Pool {
    /**
     * Amount processors in this machine.
     * Fixed amount thread which maybe work parallel in this pool.
     */
    private final int proc;
    /**
     * List actual which started.
     */
    private final List<Thread> actual;
    /**
     * Queue actual which wait when processor is released.
     */
    private final Queue<Thread> future;
    /**
     * Flag which use for kill all thread's pool.
     */
    private final AtomicBoolean shutdown;
    /**
     * Reference on service for replace dead thread on actual tasks.
     * Initializing in method add.
     */
    private Thread rotate = null;
    /**
     * Default constructor.
     */
    public ThreadPool() {
        this.proc = Runtime.getRuntime().availableProcessors();
        this.actual = new CopyOnWriteArrayList<>();
        this.future = new LinkedBlockingQueue<>();
        this.shutdown = new AtomicBoolean(false);
        //this.rotationDeadThreads();
    }

    /**
     * Delete all thread in queue, and call interrupt for other actual.
     */
    @Override
    public void shutdown() {
        shutdown.set(true);
        future.clear();
        actual.forEach(Thread::interrupt);
    }

    /**
     * Show was it shutdown this pool.
     * @return true if shutdown() was call, else false.
     */
    @Override
    public boolean isShutdown() {
        return shutdown.get();
    }

    /**
     * Add thread in pool.
     * If we have free processor add in actual, else add in future.
     * @param work any Runnable object.
     */
    @Override
    public <T extends Runnable> void add(final T work) {
        if (rotate == null) rotationDeadThreads();

        final Thread thread = new Thread(work);

        if (actual.size() < proc) {
            actual.add(thread);
            thread.start();
            return;
        }

        future.add(thread);
    }

    /**
     * Thread-service which for search free processor.
     * When found dead thread then dead thread replace on thread of queue future.
     * Service check statement threads every 10 milliseconds.
     */
    private void rotationDeadThreads() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!shutdown.get()) {
                    replaceDeadTreadsOnActualTasks();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void replaceDeadTreadsOnActualTasks() {
                for (Thread thread : actual) {
                    if (!thread.isAlive()) {
                        actual.remove(thread);
                        final Thread next = future.poll();
                        if (next == null) return;
                        actual.add(next);
                        next.start();
                    }
                }
            }

        }).start();
    }
}

