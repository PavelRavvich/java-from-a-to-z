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
}
