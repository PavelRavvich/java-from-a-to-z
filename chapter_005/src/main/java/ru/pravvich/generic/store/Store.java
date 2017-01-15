package ru.pravvich.generic.store;

interface Store<T extends Base> {
    void add(T user);
}
