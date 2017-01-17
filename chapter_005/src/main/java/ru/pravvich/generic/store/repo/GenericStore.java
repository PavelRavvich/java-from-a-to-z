package ru.pravvich.generic.store.repo;

import ru.pravvich.generic.store.Base;
import ru.pravvich.generic.store.simple_array.Simple;
import ru.pravvich.generic.store.simple_array.SimpleArray;

public class GenericStore<T extends Base> implements Store<T> {
    private Simple<T> users = new SimpleArray<>();

    @Override
    public void add(T subject) {
        users.add(subject);
    }

    public Simple<T> getStore() {
        return users;
    }

    @Override
    public boolean delete(T subject) {
        for (int i = 0; i < users.getSize(); i++) {
            if (subject.getId().equals(users.get(i).getId())) {
                users.delete(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(T oldObj, T newObj) {
        for (int i = 0; i < users.getSize(); i++) {
            if (oldObj.getId().equals(users.get(i).getId())) {
                users.update(i, newObj);
                return true;
            }
        }
        return false;
    }
}
