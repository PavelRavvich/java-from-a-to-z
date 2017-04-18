package nonBlockingCash.cash;

import nonBlockingCash.taskModel.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param <K> - id.
 * @param <V> - model.
 * @author Pavel Ravvich.
 *         Non blocking cash.
 */
public class NonBlockingCash<K extends Number, V extends Task> implements Cash<K, V> {
    /**
     * Data structure for parallel access.
     */
    private final Map<K, V> cash;

    /**
     * Default constructor.
     */
    public NonBlockingCash() {
        this.cash = new ConcurrentHashMap<>();
    }

    /**
     * Update model state.
     *
     * @return true if update success, and version increment.
     * If cash not contains key return false.
     * throw DataIsLostException if version not equals or cash not contains idKey.
     */
    @Override
    public boolean update(final K idKey, final V newValue) {
        return this.cash.replace(idKey, this.cash.get(idKey), newValue);
    }

    /**
     * Addition element in cash.
     *
     * @return true if addition is success, else false.
     */
    @Override
    public boolean add(final K key, final V value) {
        if (this.cash.containsKey(key)) return false;
        this.cash.put(key, value);
        return true;
    }

    /**
     * Overload method for get from Walker.
     *
     * @return value, or null.
     */
    @Override
    public V get(final K idKey, final V userVersion) {
        return this.cash.get(idKey);
    }

    public V contains(K idKey) {
        return this.cash.get(idKey);
    }

    /**
     * Increment version task by idKey.
     *
     * @param idKey for increment.
     * @return true if this global cash contain idKey, else false.
     */
    @Override
    public boolean incrementVersion(final K idKey) {
        if (!this.cash.containsKey(idKey)) return false;
        this.cash.get(idKey).incrementVersion();
        return true;
    }

    /**
     * Delete element in cash.
     *
     * @return true if delete is success, else false.
     */
    @Override
    public boolean delete(final K idKey) {
        return this.cash.remove(idKey, this.cash.get(idKey));
    }
}


