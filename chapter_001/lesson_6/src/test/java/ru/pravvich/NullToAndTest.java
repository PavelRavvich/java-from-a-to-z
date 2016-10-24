package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NullToAndTest {

    /**
     * Method:
     * @see NullToAnd#nullGoEnd(String[])
     */
    @Test
    public void WhenArrayWithNullsInThenAllNullsGoToAndArray() {
        String[] arr = {"a", "b", "c", null, null, "d", "e", "f"};
        NullToAnd nullToAnd = new NullToAnd();
        String[] result = nullToAnd.nullGoEnd(arr);
        String[] check = {"a", "b", "c","d", "e", "f", null, null};
        assertThat(result, is(check));
    }
}
