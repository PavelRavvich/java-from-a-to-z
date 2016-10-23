package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindSubstringCharTest {

    /**
     * Проверяет метод ищущий подстроку в строке.
     * @see FindSubstringChar#contain(String, String)
     */
    @Test
    public void WhenSetStringAndSubstringThenFindSubstringInString() {
        FindSubstringChar findSubstring = new FindSubstringChar();
        String origin = "sourse";
        String sub = "ou";
        boolean result = findSubstring.contain(origin, sub);
        boolean check = true;
        assertThat(result, is(check));
    }


    /**
     * Проверяет метод ищущий подстроку в строке.
     * @see FindSubstringChar#contain(String, String)
     */
    @Test
    public void WhenSetStringAnSubstringThenFindSubstringInString() {
        FindSubstringChar findSubstring = new FindSubstringChar();
        String origin = "sourse";
        String sub = "ok";
        boolean result = findSubstring.contain(origin, sub);
        boolean check = false;
        assertThat(result, is(check));
    }
}