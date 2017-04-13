package nonBlockingCash.user;

import nonBlockingCash.cash.Cash;
import nonBlockingCash.taskModel.ModelTask;
import nonBlockingCash.taskModel.Task;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class User <K extends Number, V extends Task> implements Account<K, V> {
    /**
     * Global non blocking cash for parallel access.
     */
    private final Cash<K, V> globalCash;
    /**
     * Local cash belongs this Walker. For match version of tasks.
     */
    private final Map<K, V> localCash;

    /**
     * Default constructor.
     * @param globalCash for parallel access.
     */
    public User(final Cash<K, V> globalCash) {
        this.localCash = new HashMap<>();
        this.globalCash = globalCash;
    }

    public User(final Cash<K, V> globalCash, int userId) {
        this.localCash = new HashMap<>();
        this.globalCash = globalCash;
    }

    /**
     * Addition in local and global cash.
     * @return true if cashes not contain duplicate key.
     * False if global and local cashes not contain idKey.
     */
    @Override
    public boolean add(final K idKey, final V task) {
        final V t = this.localCash.get(idKey);
        if (t != null) return false;
        this.localCash.put(idKey, (V) new ModelTask(task));
        return this.globalCash.add(idKey, task);
    }

    /**
     * Update task.
     * Incrementing version both cashes.
     * @param idKey id of task for update/key for Map.
     * @param name new name for task.
     * @return true if success, else false.
     */
    @Override
    public boolean update(K idKey, final String name) {
        //если в локаотном кэше нет то добавить из глобольного последнюю версию
        if (!this.localCash.containsKey(idKey)) {
            final V globalVersion = this.globalCash.contains(idKey);
            if (globalVersion == null) {
                return false;
            }

            this.localCash.put(idKey, (V) new ModelTask(globalVersion));
        }

        final V userVersion = this.localCash.get(idKey);
        userVersion.setName(name);

        //increment version in both cashes.
        this.localCash.get(idKey).incrementVersion();
        this.globalCash.incrementVersion(idKey);
        return this.globalCash.update(idKey, userVersion);
    }

    /**
     * Get task from global cash.
     * @param idKey id of task/key for Map.
     * @return <\V extends Task> if task exist and versions equals.
     * False if value by idKey not contains.
     */
    @Override
    public V get(final K idKey) {
        final V userVersion = this.localCash.get(idKey);
        if (userVersion == null && this.globalCash.contains(idKey) != null) {
            this.localCash.put(idKey, this.globalCash.contains(idKey));
        }

        if (userVersion == null && this.globalCash.contains(idKey) == null) return null;

        return this.globalCash.get(idKey, userVersion);
    }

    /**
     * Delete task. Don't check versions.
     * @param idKey id of task for delete.
     * @return true if deleted successful in global cash.
     * Contains of element in local cash not important.
     */
    @Override
    public boolean delete(final K idKey) {
        return this.globalCash.delete(idKey);
    }


}
