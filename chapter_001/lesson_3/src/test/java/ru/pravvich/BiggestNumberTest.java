package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BiggestNumberTest {
    @Test
    public void WhenSetArrayThenReturnBiggestNumberInArray() {
        BiggestNumber biggestNumber = new BiggestNumber();
        int[] value = {23, 12, 567, -123, 34, 3, 98};
        int correctAnswer = 567;
        biggestNumber.finderBiggest(value);
        int result = biggestNumber.getResult();
        assertThat(result, is(correctAnswer));
    }
}