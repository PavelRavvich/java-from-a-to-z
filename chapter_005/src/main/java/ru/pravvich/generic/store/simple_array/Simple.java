package ru.pravvich.generic.store.simple_array;

public interface Simple<E> {
    boolean add(E e);
    void update(int index, E element);
    void delete(int index);
    E get(int index);
    int getSize();
}
