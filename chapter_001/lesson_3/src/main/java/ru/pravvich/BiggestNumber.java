package ru.pravvich;

public class BiggestNumber {
    int max;

    public int getResult() {
        return max;
    }

    /* Сортирует массив, и находит самое большое значение. */
    public void finderBiggest(int[] value) {
        for (int i = 0; i < value.length; i++) {
            if (max < value[i]) {
                max = value[i];
            } else {
                int heap = value[i];
            }
        }
    }
}
