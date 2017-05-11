package ru.pravvich.jdbs.requestGeneration.requestBuilder;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class DBExistsTest {
    @Test
    public void whenCallBuildThenReturnScriptForCheckWhatDBIsExist() {
        RequestBuilder rb = new DBExists();
        final String result = rb.build();
        Assert.assertThat(result, is("select 1 as " +
                "result from pg_database where datname = 'auto_services'"));
    }

    @Test
    public void whenTypeIsEqualThenReturnTrue() {
        RequestBuilder rb = new DBExists();
        final boolean result = rb.requestTypeEqual("DBExist");
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenTypeNotEqualThenReturnFalse() {
        RequestBuilder rb = new DBExists();
        final boolean result = rb.requestTypeEqual("some flag");
        Assert.assertThat(result, is(false));
    }
}