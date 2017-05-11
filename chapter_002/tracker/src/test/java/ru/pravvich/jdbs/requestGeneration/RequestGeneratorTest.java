package ru.pravvich.jdbs.requestGeneration;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class RequestGeneratorTest {

    @Test
    public void whenThen() {
        Generator g = new RequestGenerator();

        final String result = g.generate("add_item");

        Assert.assertThat(result, is("insert into tasks (task_id," +
                " author, header, description, create_time) value" +
                "s (default, (?), (?), (?), now()::timestamp)"));
    }


}