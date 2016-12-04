package ru.pravvich.lesson_4;

import java.util.Arrays;
import java.util.Collections;

class PalindromeCheck {
    boolean check(String value) {
        int l = value.length();
        value = value.toUpperCase();
        String[] fst = value.substring(0, l / 2).split("");
        String[] scd = value.substring(l - (l / 2), l).split("");
        Arrays.sort(scd, Collections.reverseOrder());
        return (l == 5) && (Arrays.equals(fst, scd));
    }
}
