package nonBlockingCash.cash;

import nonBlockingCash.taskModel.Task;

public interface Cash<K extends Number, V extends Task> extends IncrementFunction<K> {
    boolean update(final K key, final V value);
    boolean add(final K key, final V value);
    V contains(K idKey);
    boolean delete(final K key);
    V get(final K key, final V userVersion);
}
