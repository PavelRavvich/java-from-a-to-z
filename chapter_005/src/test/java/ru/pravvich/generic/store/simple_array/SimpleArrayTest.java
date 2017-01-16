package ru.pravvich.generic.store.simple_array;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    @Test
    public void whenAddElementSuccessThenReturnTrue() {
        Simple<String> simpleArray = new SimpleArray<>();
        boolean result = simpleArray.add("1");

        assertThat(result, is(true));
    }

    @Test
    public void whenGetElementByIndexThenReturnValueByIndex() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");

        String result = simpleArray.get(0);
        assertThat(result, is("1"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementWhichNotExistThenThrowIndexOutOfBoundsException() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");
        simpleArray.get(1);
    }

    @Test
    public void whenUpdateElementWhichExistThenOldElementReplaceToNewElement() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");

        simpleArray.update(0, "2");
        assertThat(simpleArray.get(0), is("2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenTryUpdateElementWhichNotExistThenThrowIndexOutOfBoundsException() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");

        simpleArray.update(1, "2");
    }

    @Test
    public void whenThenElementDelete() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");

        simpleArray.delete(1);
        assertThat(simpleArray.getSize(), is(2));
        assertThat(simpleArray.get(0), is("1"));
        assertThat(simpleArray.get(1), is("3"));
    }

    @Test
    public void whenElementForDeleteIsLastInArrayThenElemDelete() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");

        simpleArray.delete(2);
        assertThat(simpleArray.getSize(), is(2));
        assertThat(simpleArray.get(0), is("1"));
        assertThat(simpleArray.get(1), is("2"));
    }

    @Test
    public void whenElementFirstInArrayThenElemDelete() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");

        simpleArray.delete(0);
        assertThat(simpleArray.getSize(), is(2));
        assertThat(simpleArray.get(0), is("2"));
        assertThat(simpleArray.get(1), is("3"));
    }

    @Test
    public void whenElementOnlyLaneInArrayThenSizeIsZero() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");

        simpleArray.delete(0);
        assertThat(simpleArray.getSize(), is(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOfElementNotExistThenThrowException() {
        Simple<String> simpleArray = new SimpleArray<>();
        simpleArray.add("1");

        simpleArray.delete(1);
    }
}