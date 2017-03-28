package moneyTransfer.parallelTasks;

import moneyTransfer.storage.Storage;
import moneyTransfer.storage.UserStorage;
import moneyTransfer.user.Account;
import moneyTransfer.user.User;

import java.math.BigDecimal;
import java.util.concurrent.*;

public class Consumer {
    private final Storage storage;
    private final ExecutorService threadPool;

    public Consumer() {
        this.storage = new UserStorage();
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    private final class AddTask implements Callable<Boolean> {
        private BigDecimal amount;
        private String name;
        private Integer id;

        private Boolean added;

        private AddTask(BigDecimal amount, String name, Integer id) {
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
            this.added = Consumer.this.storage.addAccount(account);
            System.out.println(Thread.currentThread().getName() + " run = "  + added);
        }
    }

    public boolean addAccount(BigDecimal amount, String name, Integer id) {
        Future<Boolean> result = threadPool.submit(new AddTask(amount, name, id));
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delAccount(Integer id) throws InterruptedException {
        ThreadDelete thread = new ThreadDelete(id);
        thread.start();
        thread.join();
        return thread.deleted;
    }

    public Account viewAccount(Integer id) throws InterruptedException {
        ThreadView thread = new ThreadView(id);
        thread.start();
        thread.join();
        return thread.account;
    }

    private class ThreadAdd extends Thread {
        private volatile boolean added;

        private BigDecimal amount;
        private String name;
        private Integer id;

        private ThreadAdd(BigDecimal amount, String name, Integer id) {
            this.amount = amount;
            this.name = name;
            this.id = id;
        }

        @Override
        public void run() {
            this.add();
        }

        private void add() {
            final Account account = new User(this.amount, this.name, this.id);
            added = Consumer.this.storage.addAccount(account);
            System.out.println(Thread.currentThread().getName() + " run = "  + added);
        }
    }

    private class ThreadDelete extends Thread {
        private boolean deleted;

        private Integer id;

        private ThreadDelete(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            this.delete();
        }

        private void delete() {
            this.deleted = Consumer.this.storage.delAccount(this.id);
        }
    }

    private class ThreadView extends Thread {
        private Account account;
        private final Integer id;

        private ThreadView(final Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            this.view();
        }

        private Account view() {
            return storage.viewAccount(this.id);
        }
    }
}
