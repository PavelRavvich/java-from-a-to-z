package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleTest {
    @Test
    public void whenSetNumbersThenSortingFromSmallToLarge() {
        double[] arr = new double[5];
        arr[0] = 91.6;
        arr[1] = 18.123;
        arr[2] = 54;
        arr[3] = 22;
        arr[4] = 14;
        Bubble bubble = new Bubble();
        double[] result = {14, 18.123, 22, 54, 91.6};
        assertThat(result,is(bubble.bubbleSort(arr)));
    }

    // Сортировка в обратном порядке
    @Test
    public void whenSetNumbersThenSortingFromLargeToSmall() {
        int[] arr = new int[5];
        arr[0] = 91;
        arr[1] = 18;
        arr[2] = 54;
        arr[3] = 22;
        arr[4] = 14;
        Bubble bubble = new Bubble();
        int[] result = {91, 54, 22, 18, 14};
        assertThat(result,is(bubble.bubbleSortBacking(arr)));
    }
}