package ru.pravvich.jdbs.requestGeneration.requestBuilder;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class GetterItemByIDTest {

    @Test
    public void whenCallBuildThenReturnRequestPatternForGetItemById() {
        final RequestBuilder rb = new GetterItemByID();
        final String result = rb.build();
        Assert.assertThat(result, is("select t.task_id, t.author, " +
                "t.header, t.description, t.create_time, c.comment" +
                "_id, c.content from tasks as t inner join comment" +
                "s as c on t.task_id = c.task_id and t.task_id = (?)")
        );
    }

    @Test
    public void whenKeyBuilderIsEqualsThen() {
        final RequestBuilder rb = new GetterItemByID();
        final boolean result = rb.requestTypeEqual("find_by_id");
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenThen() {
        final RequestBuilder rb = new GetterItemByID();
        final boolean result = rb.requestTypeEqual("some key");
        Assert.assertThat(result, is(false));
    }
}