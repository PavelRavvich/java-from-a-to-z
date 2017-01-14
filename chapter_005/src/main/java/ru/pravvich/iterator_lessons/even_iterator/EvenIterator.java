package ru.pravvich.iterator_lessons.even_iterator;

import java.util.Iterator;

public class EvenIterator<T> implements Iterator {
    private final T[] values;
    private int index = 2;

    public EvenIterator(final T[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public T next() {
        T value = values[index];
        this.index += 2;
        return value;
    }
}