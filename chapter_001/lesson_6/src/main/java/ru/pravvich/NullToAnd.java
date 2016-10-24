package ru.pravvich;

/**
 * Отправляет все null в конец массива.
 * @author Pavel Ravvich
 * @since 24.10.2016
 * @author triodjangopiter@yandex.ru
 * @version 1.0
 */
public class NullToAnd {

    /**
     * Test:
     * @see NullToAndTest#WhenArrayWithNullsInThenAllNullsGoToAndArray()
     */
    public String[] nullGoEnd(String[] origin) {
        int count = 0;

        // Определяет колличество null и записывает в count.
        for (int x = 0; x < origin.length; x++) {
            if (origin[x] == null) {
                count++;
            }
        }

        //Переставляет null в конец массива.
        for (int j = 0; j <= count; j++) {
            for (int i = 0; i < origin.length - 1; i++) {
                if (origin[i] == null) {
                    String var = origin[i];
                    origin[i] = origin[i + 1];
                    origin[i + 1] = var;
                }
            }
        }
        return origin;
    }
}