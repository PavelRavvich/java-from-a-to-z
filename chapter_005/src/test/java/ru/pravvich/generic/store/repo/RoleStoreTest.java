package ru.pravvich.generic.store.repo;

import org.junit.Test;
import ru.pravvich.generic.store.Role;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenThenAddInRoleStoreOneRole() {
        Store<Role> store = new RoleStore();
        store.add(new Role("10"));

        assertThat(store.getStore().get(0).getId(), is("10"));
    }

    @Test
    public void whenThenAddManyObjectsRole() {
        Store<Role> store = new RoleStore();
        for (int i = 0; i < 10; i++) {
            store.add(new Role("1"));
        }

        assertThat(store.getStore().getSize(), is(10));
    }
}