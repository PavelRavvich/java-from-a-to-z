package ru.pravvich.iterator_lessons.iterator_of_iterators;

import java.util.Iterator;

/**
 * Iterator of iterators.
 * @param <T> any type which extends Iterator.
 */
public class IteratorOfIterators <T extends Iterator<Integer>> implements Iterator {
    /**
     * Iterators for processing.
     */
    private final T[] iterators;
    /**
     * Pointer for move forward.
     */
    private int index = 0;

    /**
     * Default constructor.
     * @param iterators for processing.
     */
    IteratorOfIterators(T[] iterators) {
        this.iterators = iterators;
    }

    /**
     * Check possible next iteration.
     * @return when current iterator is last, and received last element - false.
     */
    @Override
    public boolean hasNext() {
        return (iterators[index].hasNext()) || !detectedLastIterator();
    }
    /**
     * Detected last iterator.
     * @return if current iterator is last - true, else - false
     */
    private boolean detectedLastIterator() {
        return !(index + 1 < iterators.length);
    }

    /**
     * Move pointer forward.
     * @return next element.
     */
    @Override
    public Integer next() {
        if (iterators[index].hasNext()) {
            return iterators[index].next();
        } else {
            return iterators[++index].next();
        }
    }
}

class SimpleIterator <T extends Number> implements Iterator {
    private final T[] values;
    private int index = 0;

    SimpleIterator(T[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public T next() {
        return values[index++];
    }
}