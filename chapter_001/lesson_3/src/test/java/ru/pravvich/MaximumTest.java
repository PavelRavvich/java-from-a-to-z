package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MaximumTest {
    @Test
    public void whenSideTriangleComparedThenReturnTheBiggest() {
        Point a = new Point(0,0);
        Point b = new Point(3,2);
        Point c = new Point(4,0);

        Maximum maximum = new Maximum();
        double result = maximum.detectionBiggestSideTriangle(a, b, c);
        assertThat(result, is(4d));
    }
}