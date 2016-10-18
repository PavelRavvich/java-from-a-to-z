package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;

public class DeleteDuplicateArrTest {

    /**
     * Проверяет результирующий метод.
     * @see DeleteDuplicateArr#resultDeleteDuplicatesInArray(String[]) метод
     */
    @Test
    public void WhenArrayInThenDuplicatesInArrayDelete() {
        String[] arrayWithDuplicates = {"0", "1", "2", "0", "0", "1", "2",};
        DeleteDuplicateArr deleteDuplicateArr = new DeleteDuplicateArr();
        String[] result = deleteDuplicateArr.resultDeleteDuplicatesInArray(arrayWithDuplicates);
        String[] check = {"0", "1", "2"};
        assertThat(result, is(check));
    }


    /**
     * Проверка метода перезаписи массива в ArrayList.
     * @see DeleteDuplicateArr#convertToList(String[]) Метод
     */
    @Test
    public void WhenSetArrayInThenConvertingCopyArrayList() {
        DeleteDuplicateArr deleteDuplicateArr = new DeleteDuplicateArr();
        String[] arrayStrings =  {"0", "1", "2", "0", "0", "1", "2",};
        ArrayList<String> result = deleteDuplicateArr.convertToList(arrayStrings);
        ArrayList<String> list = new ArrayList<String>();
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(0, "0");
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        assertThat(result, is(list));
    }

    /**
     * Прверяет метод удаляющий дубликаты элементов.
     * @see DeleteDuplicateArr#deleteDuplicate(ArrayList)
     */
    @Test
    public void WhenArrayListInThenDuplicateCellDelete() {
        DeleteDuplicateArr deleteDuplicateArr = new DeleteDuplicateArr();
        ArrayList<String> list = new ArrayList<String>();
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add(0, "0");
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        ArrayList<String> result = deleteDuplicateArr.deleteDuplicate(list);
        ArrayList<String> check = new ArrayList<String>();
        check.add(0, "0");
        check.add(1, "1");
        check.add(2, "2");
        assertThat(result,is(check));
    }

    /**
     * Проверяет метод пеерезаписи ArrayList в массив
     * @see DeleteDuplicateArr#convertToArray(ArrayList) метод
     * */
    @Test
    public void WhenSetArrayListInThenCopyContentArrayOut() {
        DeleteDuplicateArr deleteDuplicateArr = new DeleteDuplicateArr();
        ArrayList<String> list = new ArrayList<String>();
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        String[] result = deleteDuplicateArr.convertToArray(list);
        String[] check = {"0", "1", "2"};
        assertThat(result, is(check));
    }
}