package ru.pravvich.parser;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PropertiesLoaderTest {

    @Test
    public void whenKeyGoInGetValueThenValueWhichAssociatedWithKeyReturn() {

        final PropertiesLoader properties = new PropertiesLoader("siteURL");

        final String result = properties.getValue("url");

        assertThat(result, is("http://www.sql.ru/forum/job-offers"));
    }

    @Test
    public void whenKeyNotExistThenReturnNull() {

        final PropertiesLoader properties = new PropertiesLoader("siteURL");

        final String result = properties.getValue("not exist key");

        assertNull(result);
    }

    @Test
    public void whenThen() {
        PropertiesLoader properties = new PropertiesLoader("testDatabase");
        final String result = properties.getValue("delete_recruiter_test");
        assertThat(result, is("drop table recruiter"));
    }

}