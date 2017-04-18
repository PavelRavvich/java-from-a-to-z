package nonBlockingCash.cash;

import nonBlockingCash.taskModel.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Pavel Ravvich.
 * Non blocking cash.
 * @param <K> - id.
 * @param <V> - model.
 */
public class NonBlockingCash<K extends Number, V extends Task> implements Cash<K, V> {
    /**
     * Data structure for parallel access.
     */
    private final Map<K, V> cash;

    private final Lock monitor;

    /**
     * Default constructor.
     */
    public NonBlockingCash() {
        this.monitor = new ReentrantLock();
        this.cash = new ConcurrentHashMap<>();
    }

    /**
     * Update model state.
     * @return true if update success, and version increment.
     * If cash not contains key return false.
     * throw DataIsLostException if version not equals or cash not contains idKey.
     */
    @Override
    public boolean update(final K idKey, final V newValue) {
        this.monitor.lock();
        try {
            final V oldValue = this.cash.get(idKey);

            if (newValue != null && oldValue == null) {
                throw new DataIsLostException();
            }

            assert newValue != null;
            if (oldValue.getVersion() != newValue.getVersion()) {
                //Walker which wanted update did increment version. It's backup.
                oldValue.decrementVersion();
                throw new DataIsLostException();
            }

            return this.cash.replace(idKey, oldValue, newValue);
        } finally {
            this.monitor.unlock();
        }
    }

    /**
     * Addition element in cash.
     * @return true if addition is success, else false.
     */
    @Override
    public boolean add(final K key, final V value) {
        this.monitor.lock();
        try {
            if (this.cash.containsKey(key)) return false;
            this.cash.put(key, value);
            return true;
        } finally {
            this.monitor.unlock();
        }
    }

    /**
     * Overload method for get from Walker.
     * @return value, or null.
     */
    @Override
    public V get(final K idKey, final V userVersion) {
        this.monitor.lock();
        try {
            if (userVersion == null && this.cash.get(idKey) == null) return null;

            if ((userVersion != null && this.cash.get(idKey) == null) ||
                    (userVersion != null && //Check different of versions.
                                    userVersion.getVersion() != this.cash.get(idKey).getVersion())
                    ) {

                throw new DataIsLostException();
            }

            return this.cash.get(idKey);
        } finally {
            this.monitor.unlock();
        }
    }

    public V contains(K idKey) {
        this.monitor.lock();
        try {
            return this.cash.get(idKey);
        } finally {
            this.monitor.unlock();
        }

    }

    /**
     * Increment version task by idKey.
     * @param idKey for increment.
     * @return true if this global cash contain idKey, else false.
     */
    @Override
    public boolean incrementVersion(final K idKey) {
        this.monitor.lock();
        try {
            if (!this.cash.containsKey(idKey)) return false;
            this.cash.get(idKey).incrementVersion();
            return true;
        } finally {
            this.monitor.unlock();
        }
    }

    /**
     * Delete element in cash.
     * @return true if delete is success, else false.
     */
    @Override
    public boolean delete(final K idKey) {
        this.monitor.lock();
        try {
            return this.cash.remove(idKey, this.cash.get(idKey));
        } finally {
            this.monitor.unlock();
        }
    }
}


