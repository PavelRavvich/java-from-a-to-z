package ru.pravvich.additionalQuestion;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CorrectBracketTest {
    @Test
    public void whenBracketIsCorrectThenReturnTrue() {
        CorrectBracket cb = new CorrectBracket();
        String check = "(()())";
        boolean result = cb.checkSecond(check);
        assertThat(result, is(true));
    }

    @Test
    public void whenBracketNotCorrectThenReturnFalse() {
        CorrectBracket cb = new CorrectBracket();
        String check = "(()()";
        boolean result = cb.checkSecond(check);
        assertThat(result, is(false));
    }
}


