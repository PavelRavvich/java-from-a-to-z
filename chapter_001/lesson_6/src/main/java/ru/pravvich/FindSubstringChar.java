package ru.pravvich;
import java.util.Arrays;

/**
 * Проверяет наличие подстроки в строке
 * @author Pavel Ravvich 18.10.21
 * @since 23.10.2016
 * @author triodjangopiter@yandex.ru
 * @version 1.0
 */
public class FindSubstringChar {

    /**
     * Проверяет является ли строка sub подстрокой origin
     * @param origin - строка по которой производится поиск.
     * @param sub - искомая строка.
     * <p>
     * Test:
     * @see FindSubstringCharTest#WhenSetStringAndSubstringThenFindSubstringInString()
     * */
    public boolean contain(String origin, String sub) {
        if (origin.length() < sub.length()) { return false; }
        char[] arrOrigin = origin.toCharArray();
        char[] arrSub = sub.toCharArray();
        for (int i = 0; i <= arrOrigin.length - arrSub.length; i++) {
            if (arrSub[0] == arrOrigin[i]) {
                char[] forEqual = new char[arrSub.length];
                System.arraycopy(arrOrigin, i, forEqual, 0, arrSub.length);
                if (Arrays.equals(forEqual, arrSub)) {
                    return true;
                }
            }
        }
        return false;
    }
}