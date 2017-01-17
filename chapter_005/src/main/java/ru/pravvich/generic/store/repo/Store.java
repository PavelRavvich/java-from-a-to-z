package ru.pravvich.generic.store.repo;

import ru.pravvich.generic.store.Base;
import ru.pravvich.generic.store.simple_array.Simple;

interface Store<T extends Base> {
    void add(T t);
    Simple<T> getStore();
    boolean delete(T t);
    boolean update(T oldObj, T newObj);
}
