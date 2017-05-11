package ru.pravvich.jdbs.requestGeneration.requestBuilder;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class AdditionItemTest {

    @Test
    public void whenBuildRequestThenReturnStringRequest() {

        final RequestBuilder rb = new AddItem();

        final String result = rb.build();
        Assert.assertThat(result, is("insert into tasks (task_id," +
                " author, header, description, create_time) value" +
                "s (default, (?), (?), (?), now()::timestamp)"));
    }

    @Test
    public void whenCallRequestTypeEqualAndTypesNotEqualsThenReturnFalse() {
        final RequestBuilder rb = new AddItem();

        final boolean result = rb.requestTypeEqual("some key");
        Assert.assertThat(result, is(false));
    }

    @Test
    public void whenCallRequestTypeEqualAndTypesIsEqualsThenReturnTrue() {
        final RequestBuilder rb = new AddItem();

        final boolean result = rb.requestTypeEqual("add_item");
        Assert.assertThat(result, is(true));
    }
}