package ru.pravvich.set.arraySimpleSet;

public interface SetArray<E> extends Iterable<E> {
    boolean add(E e);
    int size();
}
