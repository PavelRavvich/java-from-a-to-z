package ru.pravvich;

/**
 * Удаляет дубликаты в массиве.
 * @author Pavel Ravvich 20.10.2016
 * @author triodjangopiter@yandex.ru
 * @version 1.0
 */
public class DuplicateWithoutCollection {
    int nulls;

    /**
     * Объединяет три метода.
     * @see #addNullInsteadDuplicate Добавление null вместо дкбликатов
     * @see #bubbleForNull(String[]) Сортировка null в конец массива
     * @see #deleteNulls(String[]) Перезапись всех null в новый массив
     * <p>
     *     Тест:
     * @see DuplicateWithoutCollectionTest#WhenArrayWithDuplicatesInThenArrayWithoutDuplicatesOut()     
     */
    public String[] finishDeleteDuplicate(String[] arr) {
        String[] addNull = addNullInsteadDuplicate(arr);
        String[] bubble = bubbleForNull(addNull);
        String[] deleteNull = deleteNulls(bubble);
        return deleteNull;
    }

    /**
     * Заменяет дубликаты на null
     * Записывает колличество нулей в переменну nulls
     * <p>
     *     Тест
     * @see DuplicateWithoutCollectionTest#WhenArrayWithDuplicateInThenArrayWithNullInsteadDupliateOut()
     */
    public String[] addNullInsteadDuplicate(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] != null && arr[j] != null && arr[i].equals(arr[j])) {
                    arr[j] = null;
                    nulls++;
                }
            }
        }
        return arr;
    }

    /***
     * Выталкиват null в конец массива.
     * <p>
     *     Тест
     * @see DuplicateWithoutCollectionTest#WhenArrayWithNullInThenAllNullsGoToTheEndArray()
     */
    public String[] bubbleForNull(String[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] == null) {
                    arr[j] = arr[j + 1];
                    arr[j + 1] = null;
                }
            }
        }
        return arr;
    }

    /**
     * Удаляет нули.
     * <p>
     *     Тест
     * @see DuplicateWithoutCollectionTest#WhenArrayWithNullInThenArrayWithoutNullOut()
     */
    public String[] deleteNulls(String[] arr) {
        String[] result = new String[arr.length - this.nulls];
        for (int i = 0; i < result.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }
}