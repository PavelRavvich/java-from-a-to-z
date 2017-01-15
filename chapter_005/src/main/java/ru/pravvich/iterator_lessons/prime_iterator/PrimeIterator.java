package ru.pravvich.iterator_lessons.prime_iterator;

import java.util.Iterator;

/**
 * Iterator which get only prime numbers.
 * @param <T> some type which extends interface Number.
 */
public class PrimeIterator<T extends Number> implements Iterator {
    /**
     * Content values for processing.
     */
    private final T[] values;
    /**
     * тут с сингл респонсобилити для этого индекса как-то не очень получилось но как по другому хз(((
     * Index moves pointer forward after use method next().
     * And contain last index to array by prime value.
     * @see #existMorePrime().
     */
    private int index = 0;

    /**
     * Default constructor.
     * @param values - array for processing.
     */
    public PrimeIterator(final T[] values) {
        this.values = values;
    }

    /**
     * Check exist next cell in values array.
     * @return exist - true, not exist - false.
     */
    @Override
    public boolean hasNext() {
        return index < values.length &&
                this.existMorePrime();
    }

    /**
     * Get next value which is prime number.
     * @return next value.
     */
    @Override
    public T next() {
        return values[index++];
    }

    /**
     * If array this.values contain prime number,
     * record index (i) by prime number, in this.index.
     * @return prime number more exist - true, not - false.
     */
    private boolean existMorePrime() {
        for (int i = index; i < values.length; i++) {
            if (isPrime(values[i])) {
                this.index = i;
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether a number is prime.
     * @param number for check.
     * @return is prime - true, not prime - false.
     */
    private boolean isPrime(final T number) {
        long num = number.longValue();

        if (num <= 0 || num == 1)
            return false;

        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}