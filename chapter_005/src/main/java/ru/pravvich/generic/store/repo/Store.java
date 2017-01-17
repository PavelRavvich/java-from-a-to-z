package ru.pravvich.generic.store.repo;

import ru.pravvich.generic.store.Base;
import ru.pravvich.generic.store.simple_array.Simple;

interface Store<T extends Base> {
    boolean update(T oldObj, T newObj);
    Simple<T> getStore();
    boolean delete(T t);
    void add(T t);
}
