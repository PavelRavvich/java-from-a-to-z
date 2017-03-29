package moneyTransfer.parallelTasks;

import moneyTransfer.user.Account;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ParallelCallableStorageTest {
    @Test
    public void whenAddAccountThenGetSuccessOfAddReturnTrueByIndexEqualId() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        consumer.addAccount(new BigDecimal("32"), "name", 1);
        Map<Integer, Boolean> allThreadResults = consumer.getSuccessOfAdd();

        boolean result = allThreadResults.get(1);
        assertThat(result, is(true));
    }

    @Test
    public void whenAddAccountWhichExistIdThenGetSuccessOfAddReturnFalseByIndexEqualId() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        consumer.addAccount(new BigDecimal("32"), "name", 1);
        consumer.getSuccessOfAdd();

        consumer.addAccount(new BigDecimal("32"), "name", 1);
        Map<Integer, Boolean> allThreadResults = consumer.getSuccessOfAdd();

        boolean result = allThreadResults.get(1);
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteAccountWhichNotExistThenReturnFalse() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        consumer.delAccount(1);
        Map<Integer, Boolean> allThreadResults = consumer.getSuccessOfDel();

        boolean result = allThreadResults.get(1);
        assertThat(result, is(false));
    }

    @Test
    public void whenAddAndDeleteAccountThenGetSuccessOfDelReturnFalseByIndexEqualId() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        consumer.addAccount(new BigDecimal("32"), "name", 1);
        consumer.delAccount(1);
        Map<Integer, Boolean> allThreadResults = consumer.getSuccessOfDel();

        boolean result = allThreadResults.get(1);
        assertThat(result, is(true));
    }

    @Test
    public void whenAddWithOriginalIdThenGetSuccessOfOperationsReturnTrue() {
        ParallelStorage consumer = new ParallelCallableStorage();
        for (int i = 0; i < 100; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }

        Map<Integer, Boolean> results = consumer.getSuccessOfAdd();
        results.values().forEach(Assert::assertTrue);
    }

    @Test
    public void whenDeleteWithExistIdThenGetSuccessOfOperationsReturnTrue() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        for (int i = 100; i < 200; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }

        for (int i = 100; i < 200; i++) {
            consumer.delAccount(i);
        }

        Map<Integer, Boolean> results = consumer.getSuccessOfDel();
        results.values().forEach(Assert::assertTrue);
    }

    @Test
    public void whenUpdateAccountWhichExistThenReturnTrue() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        consumer.addAccount(new BigDecimal("32"), "name", 1);
        consumer.getSuccessOfAdd();

        consumer.updateAccount(1, "new name");
        final Map<Integer, Boolean> successOfUpdate = consumer.getSuccessOfUpdate();

        Boolean result = successOfUpdate.get(1);
        assertTrue(result);
    }

    @Test
    public void whenUpdateAccountWhichNotExistThenReturnFalse() {
        final ParallelStorage consumer = new ParallelCallableStorage();

        consumer.updateAccount(1, "new name");
        final Map<Integer, Boolean> successOfUpdate = consumer.getSuccessOfUpdate();

        Boolean result = successOfUpdate.get(1);
        assertFalse(result);
    }

    @Test
    public void whenManyParallelUpdateAccountWhichExistThenAllResultsIsTrue() {
        final ParallelStorage consumer = new ParallelCallableStorage();

        for (int i = 100; i < 200; i++) {
            consumer.addAccount(new BigDecimal("32"), "name", i);
        }

        for (int i = 100; i < 200; i++) {
            consumer.updateAccount(i, "new name");
        }

        final Map<Integer, Boolean> successOfUpdate = consumer.getSuccessOfUpdate();
        successOfUpdate.values().forEach(Assert::assertTrue);

    }

    @Test
    public void whenViewAccountWhichExistThenIdIsEquals() {
        final ParallelStorage consumer = new ParallelCallableStorage();
        consumer.addAccount(new BigDecimal("32"), "name", 1);

        consumer.viewAccount(1);
        final Map<Integer, Account> successOfView = consumer.getSuccessOfView();
        final Account result = successOfView.get(1);

        assertThat(result.getId(), is(1));
    }

    @Test
    public void whenManyParallelUpdateAccountNotWhichExistThenReturnAllValuesFalse() {
        final ParallelStorage consumer = new ParallelCallableStorage();

        for (int i = 100; i < 200; i++) {
            consumer.updateAccount(i, "new name");
        }

        final Map<Integer, Boolean> successOfUpdate = consumer.getSuccessOfUpdate();
        successOfUpdate.values().forEach(Assert::assertFalse);
    }
}