package ru.pravvich.jdbc;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for PropertiesLoader class.
 */
public class PropertiesLoaderTest {

    @Test
    public void whenKeyExistsThenReturnValue() {
        final PropertiesLoader loader = new PropertiesLoader("database_scripts");
        final String result = loader.get("get");
        assertThat(result, is("SELECT * FROM users WHERE id = (?)"));
    }


    @Test
    public void whenKeyNotExistsThenReturnNull() {
        final PropertiesLoader loader = new PropertiesLoader("database_scripts");
        final String result = loader.get("not_exist_key");
        assertNull(result);
    }

}