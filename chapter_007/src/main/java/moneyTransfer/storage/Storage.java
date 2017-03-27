package moneyTransfer.storage;

import moneyTransfer.user.Account;

public interface Storage {
    boolean updateAccount(final Integer id, final String name);
    boolean addAccount(final Account account);
    boolean delAccount(final Integer id);
    Account viewAccount(final Integer id);
}
