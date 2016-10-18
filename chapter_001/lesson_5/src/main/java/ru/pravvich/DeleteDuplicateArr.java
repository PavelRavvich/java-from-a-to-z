package ru.pravvich;

import java.util.ArrayList;

/**
 * Удаляет дубликаты ячеек в массиве.
 * @author Pavel Ravvich 18.10.16.
 * @author triodjangopiter@yandex.ru
 * @version 1.0
 */
public class DeleteDuplicateArr {

    /**
     * Объединяет три метода:
     * <ol>
     *     <li>Конветирование в ArrayList.
     * {@link #convertToList(String[])}
     *     </li>
     *      <li>
     *     Удаляение дубликатов ячеек.
     * {@link #deleteDuplicate(ArrayList)}
     *     </li>
     *      <li>
     *     Конвертация ArrayList обратно в массив.
     * {@link #convertToArray(ArrayList)}
     *      </li>
     * </ol>
     * <p>
     * @see DeleteDuplicateArrTest#WhenArrayListInThenDuplicateCellDelete() Тест
     * @param startArray - оригинальный массив.
     */
    public String[] resultDeleteDuplicatesInArray(String[] startArray) {
        ArrayList<String> list = convertToList(startArray);
        ArrayList<String> listWithOutDuplicates = deleteDuplicate(list);
        String[] resultArrayWithoutDuplicates = convertToArray(listWithOutDuplicates);
        return resultArrayWithoutDuplicates;
    }

    /**
     * Перезаписывает исходный массив в ArrayList,
     * с аналогичным наполнением.
     * <p>
     *     Test:
     * @see DeleteDuplicateArrTest#WhenSetArrayInThenConvertingCopyArrayList() Тест
     * <p>
     * @param array - изначальный массив-исходник.
     */
    public ArrayList convertToList(String[] array) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    /**
     * Удаляет дубликаты ячеек.
     * <p>
     *     Test:
     * @see DeleteDuplicateArrTest#WhenArrayListInThenDuplicateCellDelete() Тест
     * @param list - ArrayList корректируемы список.
     * */
    public ArrayList deleteDuplicate(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1, t = 0; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                    j--;
                }
            }
        }
        return list;
    }

    /**
     * Конвертирует ArrayList обратно в массив
     * <p>
     *     Test:
     * @see DeleteDuplicateArrTest#WhenSetArrayListInThenCopyContentArrayOut() тест
     * <p>
     * @param list - исходный список
     * */
    public String[] convertToArray(ArrayList<String> list) {
        String[] resultArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resultArray[i] = list.get(i);
        }
        return resultArray;
    }
}