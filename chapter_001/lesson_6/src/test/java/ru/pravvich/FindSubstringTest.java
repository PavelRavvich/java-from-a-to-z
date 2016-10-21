package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FindSubstringTest {

    /**
     * Проверяет метод ищущий подстроку в строке.
     * @see FindSubstring#contain(String, String) метод
     */
    @Test
    public void WhenSetStringAndSubstringThenFindSubstringInString() {
        FindSubstring findSubstring = new FindSubstring();
        String origin = "abcd";
        String sub = "bc";
        boolean result = findSubstring.contain(origin, sub);
        boolean check = true;
        assertThat(result, is(check));
    }
}