package moneyTransfer.parallelTasks;

import moneyTransfer.user.Account;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.Future;

public interface ParallelStorage {
    Map<Integer, Boolean> getSuccessOfAdd();
    Map<Integer, Boolean> getSuccessOfDel();
    Map<Integer, Account> getSuccessOfView();
    Map<Integer, Boolean> getSuccessOfUpdate();

    void viewAccount(final Integer id);
    void delAccount(final Integer id);
    void updateAccount(final Integer id,
                       final String name
    );

    void addAccount(final BigDecimal amount,
                    final String name,
                    final Integer id
    );
}
