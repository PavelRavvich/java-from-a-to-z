package moneyTransfer.parallelTasks;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class ParallelCallableStorageTest {
    private static final ParallelStorage consumer = new ParallelCallableStorage();

    @BeforeClass
    public static void initAccounts() {
        for (int i = 100; i < 200; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }
    }

    @Test
    public void whenAddWithOriginalIdThenGetSuccessOfOperationsReturnTrue() {
        ParallelStorage consumer = new ParallelCallableStorage();
        for (int i = 0; i < 100; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }

        Map<Integer, Boolean> results = consumer.getSuccessOfOperations();
        results.values().forEach(Assert::assertTrue);
    }

    @Test
    public void whenDeleteWithExistIdThenGetSuccessOfOperationsReturnTrue() {
        for (int i = 100; i < 200; i++) {
            consumer.delAccount(i);
        }

        Map<Integer, Boolean> results = consumer.getSuccessOfOperations();
        results.values().forEach(Assert::assertTrue);
    }
}