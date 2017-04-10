package nonBlockingCash.cash;

import nonBlockingCash.taskModel.Task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
     * Default constructor.
     */
    public NonBlockingCash() {
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
        final V oldValue = this.cash.get(idKey);

        if (newValue != null && oldValue == null) {
            throw new DataIsLostException();
        }

        assert newValue != null;
        if (oldValue.getVersion() != newValue.getVersion()) {
            //User which wanted update did increment version. It's backup.
            oldValue.decrementVersion();
            throw new DataIsLostException();
        }

        return this.cash.replace(idKey, oldValue, newValue);
    }

    /**
     * Addition element in cash.
     * @return true if addition is success, else false.
     */
    @Override
    public boolean add(final K key, final V value) {
        if (this.cash.containsKey(key)) return false;
        this.cash.put(key, value);
        return true;
    }

    /**
     * Overload method for get from User.
     * @return value, or null.
     */
    @Override
    public V get(final K idKey, final V userVersion) {
        if (userVersion == null && this.cash.get(idKey) == null) return null;

        if ((userVersion != null && this.cash.get(idKey) == null) ||
                (
                        userVersion != null && //Check different of versions.
                        userVersion.getVersion() != this.cash.get(idKey).getVersion()
                )) {

            throw new DataIsLostException();
        }

        return this.cash.get(idKey);
    }

    public V contains(K idKey) {
        return this.cash.get(idKey);
    }

    /**
     * Increment version task by idKey.
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
     * @return true if delete is success, else false.
     */
    @Override
    public boolean delete(final K idKey) {
        return this.cash.remove(idKey, this.cash.get(idKey));
    }
}


