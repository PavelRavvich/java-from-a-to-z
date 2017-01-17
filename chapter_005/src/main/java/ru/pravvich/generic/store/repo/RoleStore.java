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

    @Override
    public boolean delete(Role user) {
        for (int i = 0; i < roles.getSize(); i++) {
            if (user.getId().equals(roles.get(i).getId())) {
                roles.delete(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Role oldObj, Role newObj) {
        for (int i = 0; i < roles.getSize(); i++) {
            if (oldObj.getId().equals(roles.get(i).getId())) {
                roles.update(i, newObj);
                return true;
            }
        }
        return false;
    }
}
