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
    private final Map<Integer, Future<Boolean>> results;

    public ParallelCallableStorage() {
        this.storage = new UserStorage();
        this.results = new ConcurrentHashMap<>();
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    @Override
    public Map<Integer, Boolean> getSuccessOfOperations() {
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

    @Override
    public void addAccount(final BigDecimal amount,
                           final String name,
                           final Integer id
    ) {

        final Callable<Boolean> task = new Addition(amount, name, id);
        final Future<Boolean> result = this.threadPool.submit(task);
        this.results.put(id, result);
    }

    private final class Addition implements Callable<Boolean> {
        private final BigDecimal amount;
        private final String name;
        private final Integer id;

        private Boolean added;

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
            this.add();
            return this.added;
        }

        private void add() {
            final Account account = new User(this.amount, this.name, this.id);
            this.added = ParallelCallableStorage.this.storage.addAccount(account);
            System.out.println(Thread.currentThread().getName() + " run = "  + added);
        }
    }

    @Override
    public void delAccount(Integer id) {
        Future<Boolean> result = this.threadPool.submit(new Deleting(id));
        this.results.put(id, result);
    }

    private final class Deleting implements Callable<Boolean> {
        private Boolean deleted;

        private final int id;

        private Deleting(int id) {
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            this.delete();
            return this.deleted;
        }

        private void delete() {
            this.deleted =
                    ParallelCallableStorage
                    .this.storage.delAccount(this.id);
        }
    }

    public Account viewAccount(Integer id) throws InterruptedException {
        //final Account result = threadPool.submit(new ViewAccount(id));
        return null;
    }

    private final class ViewAccount implements Callable<Account> {
        private final Integer id;

        private Account account;

        private ViewAccount(Integer id) {
            this.id = id;
        }

        @Override
        public Account call() throws Exception {
            return account;
        }

        private void getAccount() {
            this.account =
                    ParallelCallableStorage.this
                            .storage.viewAccount(this.id);
        }
    }

}
