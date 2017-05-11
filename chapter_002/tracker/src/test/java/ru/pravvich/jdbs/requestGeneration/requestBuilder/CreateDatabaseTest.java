package ru.pravvich.jdbs.requestGeneration.requestBuilder;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class CreateDatabaseTest {
    @Test
    public void getScriptForCreateDatabase() {
        final RequestBuilder rb = new CreateDatabase();
        final String result = rb.build();
        Assert.assertThat(result, is("create database auto_services"));
    }

    @Test
    public void whenFlagIsEqualsThenReturnTrue() {
        final RequestBuilder rb = new CreateDatabase();
        final boolean result = rb.requestTypeEqual("create_database");
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenFlagNotEqualsThenReturnFalse() {
        final RequestBuilder rb = new CreateDatabase();
        final boolean result = rb.requestTypeEqual("some_flag");
        Assert.assertThat(result, is(false));
    }

}