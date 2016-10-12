package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactorialTest {
    @Test
    public void whenSetNumberThenCalculateFactorialSingleNumber() {
        Factorial factorial = new Factorial();
        int result = factorial.calculateFactorialsBetweenZeroToNine(5);
        assertThat(result,is(120));
    }
}