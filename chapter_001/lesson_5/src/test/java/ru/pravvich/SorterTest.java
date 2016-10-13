package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SorterTest {
    @Test
    public void whenSetNumberThenCalculateFactorialSingleNumber() {
        Sorter sorter = new Sorter();
        int result = sorter.calculateFactorialsBetweenZeroToNine(5);
        assertThat(result,is(120));
    }
}