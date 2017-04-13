package moneyTransfer.storage;

import moneyTransfer.user.Account;
import moneyTransfer.user.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WalkerStorageTest {
    @Test
    public void whenAddNewAccountThenMethodAddAccountReturnTrue() {
        Account account = new User(new BigDecimal("1"), "name", 1);
        Storage storage = new UserStorage();

        boolean result = storage.addAccount(account);
        assertTrue(result);
    }

    @Test
    public void whenAddNewAccountButAccountIdIsExistThenMethodAddAccountReturnTrue() {
        Account account = new User(new BigDecimal("1"), "name", 1);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        boolean result = storage.addAccount(account);
        assertFalse(result);
    }

    @Test
    public void whenAddNewAccountThenMethodViewAccountReturnAccountById() {
        Account account = new User(new BigDecimal("1"), "name", 1);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        Account result = storage.viewAccount(1);
        assertThat(result, is(account));
    }

    @Test
    public void whenUpdateAccountCallThenReturnTrue() {
        Account account = new User(new BigDecimal("1"), "name", 1);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        boolean result = storage.updateAccount(1, "new name");
        assertThat(result, is(true));
    }

    @Test
    public void whenUpdateAccountCallAndAccountIdNotExistThenReturnFalse() {
        Account account = new User(new BigDecimal("1"), "name", 2);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        boolean result = storage.updateAccount(1, "new name");
        assertThat(result, is(false));
    }

    @Test
    public void whenUpdateAccountCallThenNameAccountUpdate() {
        Account account = new User(new BigDecimal("1"), "name", 2);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        storage.updateAccount(2, "new value");
        String result = storage.viewAccount(2).getName();
        assertThat(result, is("new value"));
    }

    @Test
    public void whenDeleteAccountWhichExistThenReturnTrue() {
        Account account = new User(new BigDecimal("1"), "name", 2);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        boolean result = storage.delAccount(2);
        assertTrue(result);
    }

    @Test
    public void whenDeleteAccountWhichNotExistThenReturnFalse() {
        Storage storage = new UserStorage();

        boolean result = storage.delAccount(2);
        assertFalse(result);
    }

    @Test
    public void whenDeleteAccountWhichExistThenReturnAccountWithIdEqualMinus1() {
        Account account = new User(new BigDecimal("1"), "name", 2);
        Storage storage = new UserStorage();
        storage.addAccount(account);

        storage.delAccount(2);
        Integer id = storage.viewAccount(2).getId();
        assertThat(id, is(-1));
    }
}