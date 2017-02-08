package ru.pravvich.tree.simpleTree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleTreeSetTest {
    @Test
    public void whenMethodGetWorkThenListWhichSortReturn() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        integers.add(4);
        integers.add(0);
        integers.add(13);
        integers.add(12);
        integers.add(40);
        integers.add(1);
        List<Integer> result = integers.get();

        List<Integer> check = new LinkedList<>();
        check.add(0);
        check.add(1);
        check.add(4);
        check.add(12);
        check.add(13);
        check.add(40);

        assertThat(result, is(check));
    }

    @Test
    public void whenIteratorWorkThenElementsGetSort() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        integers.add(4);
        integers.add(0);
        integers.add(13);
        integers.add(12);
        integers.add(40);
        integers.add(1);
        List<Integer> result = new LinkedList<>();

        for (Integer i : integers) {
            result.add(i);
        }

        List<Integer> check = new LinkedList<>();
        check.add(0);
        check.add(1);
        check.add(4);
        check.add(12);
        check.add(13);
        check.add(40);

        assertThat(result, is(check));
    }

    @Test
    public void whenSizeWorkThenSizeGetAmountOfElements() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        integers.add(4);
        integers.add(0);
        integers.add(13);
        int result = integers.size();
        assertThat(result, is(3));
    }

    @Test
    public void whenAddTwoEqualsElementThenSizeNotChange() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        integers.add(4);
        integers.add(4);

        int result = integers.size();
        assertThat(result, is(1));
    }

    @Test
    public void whenAddTwoEqualsElementThenAddOnlyFirst() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        integers.add(4);
        integers.add(4);

        List<Integer> result = new LinkedList<>();

        for (Integer i : integers) {
            result.add(i);
        }

        assertThat(result.size(), is(1));
        assertThat(result.get(0), is(4));
    }

    @Test
    public void whenNoOneElementExistThenSizeEqualZero() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        List<Integer> list = integers.get();

        int result = list.size();
        assertThat(result, is(0));
    }
}