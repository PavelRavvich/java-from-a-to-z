package ru.pravvich.list.linkedList;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedContainerTest {
    @Test
    public void whenThenAddLastOneElement() {
        LinkedContainer<String> strings = new LinkedContainer<>();
        strings.addLast("1");

        String result = strings.getElementByIndex(0);
        assertThat(result, is("1"));
    }

    @Test
    public void whenThenAddLastSetElements() {
        LinkedContainer<String> strings = new LinkedContainer<>();
        strings.addLast("1");
        strings.addLast("2");
        strings.addLast("3");

        List<String> result = new ArrayList<>(100);
        for (String s : strings) {
            result.add(s);
        }

        assertThat(result.get(0), is("1"));
        assertThat(result.get(1), is("2"));
        assertThat(result.get(2), is("3"));
    }

    @Test
    public void whenThenAddFirstOneElement() {
        LinkedContainer<String> strings = new LinkedContainer<>();
        strings.addFirst("1");

        String result = strings.getElementByIndex(0);
        assertThat(result, is("1"));
    }

    @Test
    public void whenThenAddFirstManyElementsAndDescendingIteratorUse() {
        LinkedContainer<String> strings = new LinkedContainer<>();
        strings.addFirst("1");
        strings.addFirst("2");
        strings.addFirst("3");

        List<String> result = new ArrayList<>(100);

        Iterator<String> descendingIterator = strings.descendingIterator();
        while (descendingIterator.hasNext()) {
            result.add(descendingIterator.next());
        }

        assertThat(result.get(0), is("1"));
        assertThat(result.get(1), is("2"));
        assertThat(result.get(2), is("3"));
    }
}