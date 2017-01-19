package ru.pravvich.iterator_lessons.even_iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator which return only even numbers.
 * @param <T> any type which extends Number.
 */
public class EvenIterator<T extends Number> implements Iterator {
    /**
     * Array of values for processing.
     */
    private final T[] values;
    /**
     * Pointer on current cell in this.values.
     */
    private int index = 0;

    /**
     * Default constructor.
     * @param values data for processing.
     */
    public EvenIterator(final T[] values) {
        this.values = values;
    }


    /**
     * Exist in forward cell with even number.
     * @return if exist - true, if not exist - false.
     */
    @Override
    public boolean hasNext() {
        return index < values.length &&
                existMoreEvenNum() &&
                isEven(values[index]);
    }

    /**
     * Exist in forward cell with even number.
     *
     * @return if exist - true, if not exist - false.
     */
    private boolean existMoreEvenNum() {
        for (int i = index; i < values.length; i++) {
            if (isEven(values[i])) {
                this.index = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Moves forward point.
     * @return value which even.
     */
    @Override
    public T next() {
        if (isEven(values[index]))
            return values[index++];

        if (existMoreEvenNum())
            return values[index++];

        throw new NoSuchElementException("Incorrect use Iterator.");
    }

    /**
     * Check whether a number is even.
     * @param value for check.
     * @return if even - true, if not even - false.
     */
    private boolean isEven(T value) {
        int val = value.intValue();
        return !(val == 0 || val == 1) &&
                (val % 2) == 0;
    }
}