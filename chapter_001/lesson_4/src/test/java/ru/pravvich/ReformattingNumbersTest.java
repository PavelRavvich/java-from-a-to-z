package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReformattingNumbersTest {
    @Test
    public void whenSetArgumentsNumberBiggestOneAndLessTenWhenNumbersReformattingInArrWithOneCell() {
        ReformattingNumbers reformattingNumbers = new ReformattingNumbers();
        int[] array = new int[1];
        array[0] = 4;
        int[] result = reformattingNumbers.determiningTheNumberOfDigitsAndSplit(4);
        assertThat(result, is(array));
    }

    @Test
    public void whenSetArgumentsNumberBiggestTenAndLessOneHundredWhenNumbersReformattingInArrWithTwoCell() {
        ReformattingNumbers reformattingNumbers = new ReformattingNumbers();
        int[] array = new int[2];
        array[0] = 3;
        array[1] = 4;
        int[] result = reformattingNumbers.determiningTheNumberOfDigitsAndSplit(34);
        assertThat(result, is(array));
    }

    @Test
    public void whenSetArgumentsNumberBiggestOneHundredAndLessThousandWhenNumbersReformattingInArrWithThreeCell() {
        ReformattingNumbers reformattingNumbers = new ReformattingNumbers();
        int[] array = new int[3];
        array[0] = 3;
        array[1] = 4;
        array[2] = 8;
        int[] result = reformattingNumbers.determiningTheNumberOfDigitsAndSplit(348);
        assertThat(result, is(array));
    }

    @Test
    public void whenArgumentNumberEqualsZeroOrOneThenNumbersReformattingInArrWithOneCellWithValueOne() {
        ReformattingNumbers reformattingNumbers = new ReformattingNumbers();
        int[] array = new int[1];
        array[0] = 1;
        int[] result = reformattingNumbers.determiningTheNumberOfDigitsAndSplit(0);
        assertThat(result, is(array));
    }
}
