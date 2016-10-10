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

    @Test
    public void whenIntArrayInWhenIntSingleOutForCalculateFacnjrial() {
        Factorial factorial = new Factorial();
        int[] array = new int[3];
        array[0] = 2;
        array[1] = 2;
        array[2] = 2;
        int result = factorial.converting(array);
        assertThat(result, is(6));
    }
}