package ru.pravvich.generic.store.repo;

import org.junit.Test;
import ru.pravvich.generic.store.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenThenAddUserInUserStore() {
        Store<User> store = new UserStore();
        store.add(new User("10"));

        assertThat(store.getStore().get(0).getId(), is("10"));
    }

    @Test
    public void whenThenAddManyUsersInUserStore() {
        Store<User> store = new UserStore();
        for (int i = 0; i < 10; i++) {
            store.add(new User("1"));
        }

        assertThat(store.getStore().getSize(), is(10));
    }
}