package ru.pravvich.generic.store.simple_array;

import java.util.Iterator;

class ArrayIterator<E> implements Iterator<E> {
    private int index = 0;
    E[] values;

    ArrayIterator() {
        values = (E[]) new Object[0];
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public E next() {
        return values[index++];
    }
}
