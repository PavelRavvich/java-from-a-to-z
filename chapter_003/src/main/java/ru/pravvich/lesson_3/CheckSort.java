package ru.pravvich.lesson_3;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class CheckSort {
    public static void main(String[] args) {
        // сортировка первый способ
        String[] arr = {"ar","h","boot"};
        Comparator<String> stringLengthComparator = new StringLengthSort();
        Arrays.sort(arr,stringLengthComparator);
        System.out.println(Arrays.toString(arr));

        //короткий без отдельного класса хз как работает
        String[] arr1 = {"ar","h","boot"};
        Comparator<String> comprator = (o1,o2) -> o1.length() - o2.length();
        Arrays.sort(arr1,comprator);
        System.out.println(Arrays.toString(arr1));

    }

    private static class StringLengthSort implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            } else {
                if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
