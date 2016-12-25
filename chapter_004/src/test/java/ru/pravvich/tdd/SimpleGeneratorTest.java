package ru.pravvich.tdd;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for replacement keys on values.
 */
public class SimpleGeneratorTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Check case then data contain all key.
     */
    @Test
    public void whenDataContainAllKeyWhichContainTextThenAllKeyReplaceToValue() throws KeyNotFoundException {
        SimpleGenerator simple = new SimpleGenerator();
        String text = "I am a ${name}, Who are ${subject}?";

        Map<String, String> data = new HashMap<>();
        data.put("${name}", "Pavel");
        data.put("${subject}", "you");

        String result = simple.generate(text, data);
        assertThat(result, is("I am a Pavel, Who are you?"));
    }

    /**
     * When method throws exception if key not found.
     *
     * @throws KeyNotFoundException if key not found.
     */
    @Test
    public void whenKeyNotFoundThenThrowsKeyNotFoundException() throws KeyNotFoundException {
        SimpleGenerator simple = new SimpleGenerator();
        String text = "I am a ${name}, Who are ${subject}?";

        Map<String, String> data = new HashMap<>();
        data.put("${name}", "Pavel");

        this.exception.expect(KeyNotFoundException.class);
        this.exception.expectMessage("Key not found.");
        simple.generate(text, data);
    }
}