package moneyTransfer.user;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WalkerTest {
    @Test
    public void whenGetAmountReturnThen() {
        Account account = new User(new BigDecimal("1"), "name", 1);
        BigDecimal result = account.getAmount();
        assertThat(result, is(new BigDecimal("1")));
    }

    @Test
    public void whenTakeMoneyThenAmountUp() {
        Account account = new User(new BigDecimal("2"), "name", 1);
        account.takeMoney(new BigDecimal("2"));

        BigDecimal result = account.getAmount();
        assertThat(result, is(new BigDecimal("4")));
    }

    @Test
    public void whenUserWantSendLessHeIsAmountHaveThenSendMoneyReturnTrue() {
        Account account = new User(new BigDecimal("5"), "name", 1);
        boolean result = account.sendMoney(new BigDecimal("3"));
        assertTrue(result);
    }

    @Test
    public void whenThen() {
        Account account = new User(new BigDecimal("5"), "name", 1);
        account.sendMoney(new BigDecimal("3"));
        BigDecimal result = account.getAmount();
        assertThat(result, is(new BigDecimal("2")));
    }

    @Test
    public void whenUserWantSendEqualHeIsAmountThenSendMoneyReturnTrue() {
        Account account = new User(new BigDecimal("5"), "name", 1);
        boolean result = account.sendMoney(new BigDecimal("5"));
        assertTrue(result);
    }

    @Test
    public void whenUserWantSendLargeHeIsAmountHaveThenSendMoneyReturnTrue() {
        Account account = new User(new BigDecimal("1"), "name", 1);
        boolean result = account.sendMoney(new BigDecimal("6"));
        assertFalse(result);
    }
}