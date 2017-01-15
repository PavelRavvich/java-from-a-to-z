package ru.pravvich.iterator_lessons.iterator_of_iterators;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorOfIteratorsTest {
    @Test
    public void whenThenIteratorOfIteratorsBypassesAllIterators() {
        SimpleIterator<Integer> i1 = new SimpleIterator<>(new Integer[]{1, 2, 3, 4});
        SimpleIterator<Integer> i2 = new SimpleIterator<>(new Integer[]{5, 6, 7});
        SimpleIterator<Integer> i3 = new SimpleIterator<>(new Integer[]{8, 9, 10, 11, 12});

        IteratorOfIterators<Iterator<Integer>> iterators =
                new IteratorOfIterators<>(new Iterator[] {i1, i2, i3});

        Integer[] result = new Integer[12];
        int i = 0;
        while (iterators.hasNext()) {
            result[i++] = iterators.next();
        }

        assertThat(result, is(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
    }
}