package ru.pravvich.generic.store.repo;

import ru.pravvich.generic.store.Role;
import ru.pravvich.generic.store.simple_array.*;

class RoleStore implements Store<Role> {
    private Simple<Role> roles = new SimpleArray<>();

    @Override
    public void add(Role role) {
        roles.add(role);
    }

    public Simple<Role> getStore() {
        return roles;
    }
}
