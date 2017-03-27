package moneyTransfer.parallelTasks;

import moneyTransfer.storage.Storage;
import moneyTransfer.storage.UserStorage;
import moneyTransfer.user.Account;
import moneyTransfer.user.User;

import java.math.BigDecimal;

public class Consumer {
    private Storage storage;

    public Consumer() {
        this.storage = new UserStorage();
    }

    public boolean addAccount(BigDecimal amount, String name, Integer id) throws InterruptedException {
        ThreadAdd thread = new ThreadAdd(amount, name, id);
        thread.start();
        thread.join();
        return thread.added;
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
        private boolean added;

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
            Account account = new User(this.amount, this.name, this.id);
            this.added = Consumer.this.storage.addAccount(account);
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
