package ru.pravvich.iterator_lessons.even_iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {
    @Test
    public void whenHasNextReturnTrueThenIteratorGetEvenNumbers() {
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        EvenIterator<Integer> evenIterator = new EvenIterator<>(numbers);

        Integer[] result = new Integer[5];
        int i = 0;
        while (evenIterator.hasNext()) {
            result[i++] = evenIterator.next();
        }

        assertThat(result, is(new Integer[]{2, 4, 6, 8, 10}));
    }

    @Test
    public void whenArrayToNumberNotContentEvenNumberThenReturnArrayZeroLength() {
        Integer[] numbers = {0, 1,};
        EvenIterator<Integer> evenIterator = new EvenIterator<>(numbers);

        List<Integer> list = new ArrayList<>();
        while (evenIterator.hasNext()) {
            list.add(evenIterator.next());
        }

        assertThat(list.size(), is(0));
    }

    @Test
    public void whenTwiceInARowCallNextThenReturnEvenNumbers() {
        Integer[] numbers = {0, 1, 2, 4, 5};
        EvenIterator<Integer> evenIterator = new EvenIterator<>(numbers);
        List<Integer> list = new ArrayList<>();

        list.add(evenIterator.next());
        list.add(evenIterator.next());

        assertThat(list.size(), is(2));
    }
}