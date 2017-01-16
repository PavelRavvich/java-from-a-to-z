package ru.pravvich.generic.store;

import ru.pravvich.generic.store.simple_array.Simple;
import ru.pravvich.generic.store.simple_array.SimpleArray;

public class UserStore implements Store<User> {
    private Simple<User> store = new SimpleArray<>();

    @Override
    public void add(User user) {
        store.add(user);
    }

    @Override
    public Simple<User> getStore() {
        return store;
    }


}
