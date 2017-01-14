package ru.pravvich.iterator_lessons.prime_iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PrimeIteratorTest {
    @Test
    public void whenArrayContainPrimeNumbersThenIteratorReturnOnlyPrimeNumbers() {
        List<Integer> check = new ArrayList<>();
        check.add(3);
        check.add(5);

        Integer[] values = {1, 3, 4, 5};
        PrimeIterator<Integer> iterator = new PrimeIterator<>(values);

        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result, is(check));
    }


}