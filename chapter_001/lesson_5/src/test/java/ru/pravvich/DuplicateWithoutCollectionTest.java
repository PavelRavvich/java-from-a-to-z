package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DuplicateWithoutCollectionTest {

    /**
     * Проверяет результирующий метод.
     * @see DuplicateWithoutCollection#finishDeleteDuplicate(String[]) метод
     */
    @Test
    public void WhenArrayWithDuplicatesInThenArrayWithoutDuplicatesOut() {
        DuplicateWithoutCollection duplicate = new DuplicateWithoutCollection();
        String[] array = {"1","1","1","3","3"};
        String[] result =  duplicate.finishDeleteDuplicate(array);
        String[] check = {"1","3"};
        assertThat(result, is(check));
    }

    /**
     * Проверяет замену дубликатов на null
     * @see DuplicateWithoutCollection#addNullInsteadDuplicate(String[])
     */
    @Test
    public void WhenArrayWithDuplicateInThenArrayWithNullInsteadDupliateOut() {
        DuplicateWithoutCollection duplicate = new DuplicateWithoutCollection();
        String[] array = {"1","1","1","3","3"};
        String[] result = duplicate.addNullInsteadDuplicate(array);
        String[] check = {"1",null,null,"3",null};
        assertThat(result, is(check));
    }

    /**
     * Проверяет перемешение всех null в конец массива.
     * @see DuplicateWithoutCollection#bubbleForNull(String[])
     */
    @Test
    public void WhenArrayWithNullInThenAllNullsGoToTheEndArray() {
        DuplicateWithoutCollection duplicate = new DuplicateWithoutCollection();
        String[] array = {"1",null,null,"3",null};
        String[] result = duplicate.bubbleForNull(array);
        String[] check = {"1","3",null,null,null};
        assertThat(result, is(check));
    }

    /**
     * Проверяет метод удаляющий нули.
     * @see DuplicateWithoutCollection#deleteNulls(String[])
     */
    @Test
    public void WhenArrayWithNullInThenArrayWithoutNullOut() {
        DuplicateWithoutCollection duplicate = new DuplicateWithoutCollection();
        String[] array = {"1","3",null,null,null};
        duplicate.nulls = 3;
        String[] result = duplicate.deleteNulls(array);
        String[] check = {"1","3"};
        assertThat(result, is(check));
    }

}