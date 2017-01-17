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

    @Test
    public void whenDeleteRoleThenContainerMinusThisRole() {
        Store<Role> store = new RoleStore();
        for (int i = 0; i < 10; i++) {
            store.add(new Role(Integer.toString(i)));
        }
        Role role = new Role("1");
        boolean result = store.delete(role);

        assertThat(store.getStore().getSize(), is(9));
        assertThat(result, is(true));
    }

    @Test
    public void whenRoleForDeleteNotExistThenReturnFalse() {
        Store<Role> store = new RoleStore();
        Role role = new Role("1");
        boolean result = store.delete(role);

        assertThat(result, is(false));
    }

    @Test
    public void whenUpdateRoleThenNewRoleReplaceOldRole() {
        Store<Role> store = new RoleStore();
        for (int i = 0; i < 10; i++) {
            store.add(new Role(Integer.toString(i)));
        }

        Role forUpdate = store.getStore().get(0);
        Role role = new Role("100");
        boolean result = store.update(forUpdate,role);

        assertThat(store.getStore().get(0).getId(), is("100"));
        assertThat(result, is(true));
    }

    @Test
    public void whenRoleForUpdateNotExistThenReturnFalse() {
        Store<Role> store = new RoleStore();
        Role role = new Role("1");
        Role role1 = new Role("1");
        boolean result = store.update(role, role1);

        assertThat(result, is(false));
    }
}