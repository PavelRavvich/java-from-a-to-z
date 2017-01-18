package ru.pravvich.iterator_lessons.even_iterator;

import java.util.Iterator;

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
     * Content only even numbers.
     * All work iterator is get from this.evenValues.
     */
    private T[] evenValues;

    /**
     * Default constructor.
     * @param values data for processing.
     */
    public EvenIterator(final T[] values) {
        this.values = values;
        initEvenValues();
    }

    /**
     * Init array to numbers which is even.
     * All work iterator is get from this.evenValues.
     */
    private void initEvenValues() {
        try {
            evenValues = (T[]) new Number[getLengthEvenValues()];
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

        for (int i = 0, j = 0; j < evenValues.length; i++) {
            if (isEven(values[i])) {
                evenValues[j++] = values[i];
            }
        }
    }


    /**
     * Exist in forward cell with even number.
     * @return if exist - true, if not exist - false.
     */
    @Override
    public boolean hasNext() {
        return index < evenValues.length;
    }

    /**
     * Moves forward point.
     * @return value which even.
     */
    @Override
    public T next() {
        return evenValues[index++];
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

    /**
     * @return amount even number in this.values.
     */
    private int getLengthEvenValues() {
        int result = 0;
        for (T value : values) {
            if (isEven(value)) {
                result++;
            }
        }
        return result;
    }
}