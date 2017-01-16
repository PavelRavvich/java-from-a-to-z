package ru.pravvich.generic.store;

import ru.pravvich.generic.store.simple_array.Simple;

interface Store<T extends Base> {
    void add(T user);
    Simple<T> getStore();
}
