package ru.pravvich;

/**
 * Проверяет наличие подстроки в строке
 * @author Pavel Ravvich 18.10.21
 * @author triodjangopiter@yandex.ru
 * @version 1.0
 */
public class FindSubstring {

    /**
     * Проверяет является ли строка sub подстрокой origin
     * @param origin - строка по которой производится поиск.
     * @param sub - искомая строка.
     * <p>
     * Test:
     * @see FindSubstringTest#WhenSetStringAndSubstringThenFindSubstringInString()
     * */
    public boolean contain(String origin, String sub) {
        if (origin.length() < sub.length()) {
            return false;
        }
        //гарантирует пространстрво по бокам
        String originWithSpace = " " + origin + " ";
        String[] find = originWithSpace.split(sub);
        if (find.length > 1) {
            return true;
        } else {
            return false;
        }
    }
}