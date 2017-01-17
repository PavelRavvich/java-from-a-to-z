package ru.pravvich.generic.store.repo;

import ru.pravvich.generic.store.User;
import ru.pravvich.generic.store.simple_array.*;

class UserStore implements Store<User> {
    private Simple<User> users = new SimpleArray<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    public Simple<User> getStore() {
        return users;
    }

    @Override
    public boolean delete(User user) {
        for (int i = 0; i < users.getSize(); i++) {
            if (user.getId().equals(users.get(i).getId())) {
                users.delete(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(User oldObj, User newObj) {
        for (int i = 0; i < users.getSize(); i++) {
            if (oldObj.getId().equals(users.get(i).getId())) {
                users.update(i, newObj);
                return true;
            }
        }
        return false;
    }
}
