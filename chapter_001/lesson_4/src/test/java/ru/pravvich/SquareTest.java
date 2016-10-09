package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void whenMethodCalculateWorkThenValueFunctionCalculate() {
        Square square = new Square(1, 1, 1);
        float result = square.calculate(2);
        assertThat(result, is(5f));
    }

    @Test
    public void whenMethodShowTakeParametersWhenPrintListResultFunctionBetweenStartAndFinish() {
        Square square = new Square(1, 1, 1);
        square.show(1,1,1);
        float result = square.getResultShow();
        assertThat(result, is(3.0f));
    }
}