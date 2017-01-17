package ru.pravvich.generic.store.repo;

import org.junit.Test;
import ru.pravvich.generic.store.Role;
import ru.pravvich.generic.store.User;

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

    //
    @Test
    public void whenDeleteUserThenContainerMinusThisRole() {
        Store<User> store = new UserStore();
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
        Store<User> store = new UserStore();
        User user = new User("1");
        boolean result = store.delete(user);

        assertThat(result, is(false));
    }

    @Test
    public void whenUpdateUserThenNewRoleReplaceOldRole() {
        Store<User> store = new UserStore();
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
        Store<User> store = new UserStore();
        User role = new User("1");
        User role1 = new User("1");
        boolean result = store.update(role, role1);

        assertThat(result, is(false));
    }
}