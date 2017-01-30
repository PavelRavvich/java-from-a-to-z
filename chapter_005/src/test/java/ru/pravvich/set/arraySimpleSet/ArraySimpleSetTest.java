package ru.pravvich.set.arraySimpleSet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArraySimpleSetTest {
    @Test
    public void whenThenAddOneElement() {
        SetArray<String> container = new ArraySimpleSet<>();
        container.add("one");

        int result = container.size();
        assertThat(result, is(1));
    }

    @Test
    public void whenThenAddTreeElement() {
        SetArray<String> container = new ArraySimpleSet<>();
        container.add("one");
        container.add("two");
        container.add("tree");

        int result = container.size();
        assertThat(result, is(3));
    }

    @Test
    public void whenAddTwoEqualsElementThenAddOnlyOne() {
        SetArray<String> container = new ArraySimpleSet<>();
        container.add("one");
        container.add("one");

        int result = container.size();
        assertThat(result, is(1));
    }

    @Test
    public void whenElementAddNotAscendingOrderByHashCodeThenActionSort() {
        SetArray<Integer> container = new ArraySimpleSet<>();
        container.add(1);
        container.add(111);
        container.add(11);

        List<Integer> result = new ArrayList<>();
        for (Integer s : container) {
            result.add(s);
        }

        assertThat(result.get(0), is(1));
        assertThat(result.get(1), is(11));
        assertThat(result.get(2), is(111));
    }
}