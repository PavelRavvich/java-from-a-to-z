package moneyTransfer.parallelTasks;

import java.math.BigDecimal;
import java.util.Map;

public interface ParallelStorage {
    Map<Integer, Boolean> getSuccessOfOperations();
    void delAccount(Integer id);
    void addAccount(final BigDecimal amount,
                    final String name,
                    final Integer id);
}
