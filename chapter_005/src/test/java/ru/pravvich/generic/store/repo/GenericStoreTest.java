package ru.pravvich.generic.store.repo;

import org.junit.Test;
import ru.pravvich.generic.store.Role;
import ru.pravvich.generic.store.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GenericStoreTest {
    @Test
    public void whenThenAddUserInUserStore() {
        GenericStore<User> store = new GenericStore<>();
        store.add(new User("10"));

        assertThat(store.getStore().get(0).getId(), is("10"));
    }

    @Test
    public void whenThenAddManyUsersInUserStore() {
        GenericStore<User> store = new GenericStore();
        for (int i = 0; i < 10; i++) {
            store.add(new User("1"));
        }

        assertThat(store.getStore().getSize(), is(10));
    }

    @Test
    public void whenDeleteUserThenContainerMinusThisRole() {
        GenericStore<User> store = new GenericStore();
        for (int i = 0; i < 10; i++) {
            store.add(new User(Integer.toString(i)));
        }
        User user = new User("1");
        boolean result = store.delete(user);

        assertThat(store.getStore().getSize(), is(9));
        assertThat(result, is(true));
    }

    @Test
    public void whenUserForDeleteNotExistThenReturnFalse() {
        GenericStore<User> store = new GenericStore();
        User user = new User("1");
        boolean result = store.delete(user);

        assertThat(result, is(false));
    }

    @Test
    public void whenUpdateUserThenNewRoleReplaceOldRole() {
        GenericStore<User> store = new GenericStore();
        for (int i = 0; i < 10; i++) {
            store.add(new User(Integer.toString(i)));
        }

        User forUpdate = store.getStore().get(0);
        User user = new User("100");
        boolean result = store.update(forUpdate, user);

        assertThat(store.getStore().get(0).getId(), is("100"));
        assertThat(result, is(true));
    }

    @Test
    public void whenUserForUpdateNotExistThenReturnFalse() {
        GenericStore<User> store = new GenericStore();
        User role = new User("1");
        User role1 = new User("1");
        boolean result = store.update(role, role1);

        assertThat(result, is(false));
    }

    @Test
    public void whenThenAddInRoleStoreOneRole() {
        GenericStore<Role> store = new GenericStore();
        store.add(new Role("10"));

        assertThat(store.getStore().get(0).getId(), is("10"));
    }

    @Test
    public void whenThenAddManyObjectsRole() {
        GenericStore<Role> store = new GenericStore();
        for (int i = 0; i < 10; i++) {
            store.add(new Role("1"));
        }

        assertThat(store.getStore().getSize(), is(10));
    }

    @Test
    public void whenDeleteRoleThenContainerMinusThisRole() {
        GenericStore<Role> store = new GenericStore();
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
        GenericStore<Role> store = new GenericStore();
        Role role = new Role("1");
        boolean result = store.delete(role);

        assertThat(result, is(false));
    }

    @Test
    public void whenUpdateRoleThenNewRoleReplaceOldRole() {
        GenericStore<Role> store = new GenericStore();
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
        GenericStore<Role> store = new GenericStore();
        Role role = new Role("1");
        Role role1 = new Role("1");
        boolean result = store.update(role, role1);

        assertThat(result, is(false));
    }
}