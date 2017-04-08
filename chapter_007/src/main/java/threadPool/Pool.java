package threadPool;

public interface Pool {
    <T extends Runnable> void add(final T work);
    boolean isShutdown();
    void shutdown();
}
