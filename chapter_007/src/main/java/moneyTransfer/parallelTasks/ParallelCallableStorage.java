package moneyTransfer.parallelTasks;

import moneyTransfer.storage.Storage;
import moneyTransfer.storage.UserStorage;
import moneyTransfer.user.Account;
import moneyTransfer.user.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ParallelCallableStorage implements ParallelStorage {

    private final Storage storage;

    private final ExecutorService threadPool;

    private final Map<Integer, Future<Boolean>> resultsOfAdditions;
    private final Map<Integer, Future<Boolean>>   resultsOfDeletes;
    private final Map<Integer, Future<Boolean>>     resultsUpdates;
    private final Map<Integer, Future<Account>>       resultsViews;

    public ParallelCallableStorage() {
        this.threadPool =  Executors.newFixedThreadPool(10);
        this.resultsOfAdditions = new ConcurrentHashMap<>();
        this.resultsOfDeletes =   new ConcurrentHashMap<>();
        this.resultsUpdates =     new ConcurrentHashMap<>();
        this.resultsViews =       new ConcurrentHashMap<>();
        this.storage =                    new UserStorage();
    }

    @Override
    public Map<Integer, Boolean> getSuccessOfAdd() {
        return getSuccessOf(this.resultsOfAdditions);
    }

    @Override
    public Map<Integer, Boolean> getSuccessOfDel() {
        return getSuccessOf(this.resultsOfDeletes);
    }

    @Override
    public Map<Integer, Boolean> getSuccessOfUpdate() {
        return getSuccessOf(this.resultsUpdates);
    }

    @Override
    public Map<Integer, Account> getSuccessOfView() {
        final Map<Integer, Account> success = new HashMap<>();
        for (Map.Entry<Integer, Future<Account>> result : resultsViews.entrySet()) {
            try {
                success.put(result.getKey(), result.getValue().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    private Map<Integer, Boolean> getSuccessOf(final Map<Integer, Future<Boolean>> results) {
        final Map<Integer, Boolean> success = new HashMap<>();
        for (Map.Entry<Integer, Future<Boolean>> result : results.entrySet()) {
            try {
                success.put(result.getKey(), result.getValue().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    /**
     * Parallel addition accounts.
     * @param amount start state of an account money.
     * @param name of user for add.
     * @param id if user for add.
     */
    @Override
    public void addAccount(final BigDecimal amount,
                           final String name,
                           final Integer id
    ) {

        final Callable<Boolean> task = new Addition(amount, name, id);
        final Future<Boolean> future = this.threadPool.submit(task);
        this.resultsOfAdditions.put(id, future);
    }

    /**
     * Provide parallel threads for each account addition operation.
     */
    private final class Addition implements Callable<Boolean> {
        private final BigDecimal amount;
        private final String name;
        private final Integer id;

        private Addition(final BigDecimal amount,
                         final String name,
                         final Integer id
        ) {

            this.amount = amount;
            this.name = name;
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            final Account account = new User(this.amount, this.name, this.id);
            return ParallelCallableStorage.this.storage.addAccount(account);
        }
    }



    /**
     * Parallel delete accounts.
     * @param id accounts.
     */
    @Override
    public void delAccount(final Integer id) {
        final Future<Boolean> future = this.threadPool.submit(new Deleting(id));
        this.resultsOfDeletes.put(id, future);
    }

    /**
     * Provide parallel threads for each account delete operation.
     */
    private final class Deleting implements Callable<Boolean> {
        private final Integer id;

        private Deleting(final Integer id) {
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            return ParallelCallableStorage
                    .this.storage.delAccount(this.id);
        }
    }



    /**
     * Parallel getter for account.
     * @param id account which get.
     */
    @Override
    public void viewAccount(final Integer id) {
        final Callable<Account> task = new ViewAccount(id);
        final Future<Account> future = threadPool.submit(task);
        this.resultsViews.put(id, future);
    }

    /**
     * Provide getter for account in parallel thread.
     */
    private final class ViewAccount implements Callable<Account> {
        private final Integer id;

        private ViewAccount(final Integer id) {
            this.id = id;
        }

        @Override
        public Account call() throws Exception {
            return ParallelCallableStorage.this
                    .storage.viewAccount(this.id);
        }
    }


    /**
     * Parallel updates account.
     * @param id of account for name's update.
     * @param name new name for account.
     */
    @Override
    public void updateAccount(final Integer id, final String name) {
        final Callable<Boolean> task = new UpdateAccount(id, name);
        final Future<Boolean> result = ParallelCallableStorage.this.threadPool.submit(task);
        ParallelCallableStorage.this.resultsUpdates.put(id, result);
    }

    private final class UpdateAccount implements Callable<Boolean> {
        private final String name;
        private final Integer id;

        private UpdateAccount(Integer id, String name) {
            this.name = name;
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            return ParallelCallableStorage.this.storage
                    .updateAccount(this.id, this.name);
        }
    }
}
