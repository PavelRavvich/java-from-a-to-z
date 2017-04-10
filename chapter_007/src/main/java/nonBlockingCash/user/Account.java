package nonBlockingCash.user;


import nonBlockingCash.taskModel.Task;

import java.util.concurrent.Callable;

public interface Account <K extends Number, V extends Task> {
    boolean add(final K idKey, final V task);
    boolean update(K idKey, final String name);
    boolean delete(final K idKey);
    V get(final K idKey);
}
