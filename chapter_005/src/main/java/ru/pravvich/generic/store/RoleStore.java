package ru.pravvich.generic.store;

import ru.pravvich.generic.store.simple_array.Simple;
import ru.pravvich.generic.store.simple_array.SimpleArray;

public class RoleStore implements Store<Role> {
    private Simple<Role> store = new SimpleArray<>();

    @Override
    public void add(Role role) {
        store.add(role);
    }

    @Override
    public Simple<Role> getStore() {
        return store;
    }
}
