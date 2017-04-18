package moneyTransfer.storage;

import moneyTransfer.user.Account;
import moneyTransfer.user.User;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserStorage implements Storage {
    private final Map<Integer, Account> accounts;
    private final Lock lock;

    public UserStorage() {
        this.accounts = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean addAccount(final Account account) {
        lock.lock();
        try {
            final Account a = this.accounts.get(account.getId());
            if (a != null) {
                System.out.println("UserStorage = " + a);
                return false;
            }

            this.accounts.put(account.getId(), account);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Account viewAccount(final Integer id) {
        lock.lock();
        try {
            final Account account = this.accounts.get(id);
            if (account != null) {
                return account;
            }

            return new User(new BigDecimal("-1"), "not found", -1);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean updateAccount(final Integer id, final String name) {
        lock.lock();
        try {
            final Account oldVersion = this.accounts.get(id);
            if (oldVersion != null) {
                final Account updatedAccount = new User(
                        oldVersion.getAmount(), name, id
                );

                this.accounts.put(id, updatedAccount);
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean delAccount(final Integer id) {
        lock.lock();
        try {
            final Account account = this.accounts.get(id);
            if (account != null) {
                this.accounts.remove(id);
                return true;
            }

            return false;
        } finally {
            lock.unlock();
        }
    }
}
