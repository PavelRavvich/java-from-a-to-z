package ru.pravvich.set.linkedSimpleSet;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LinkedSimpleSetTest {
    @Test
    public void whenThenAddFirstOneElement() {
        LinkedSimpleSet<String> container = new LinkedSimpleSet<>();
        container.addFirst("1");

        assertThat(container.size(), is(1));
    }

    @Test
    public void whenThenAddFirstManyDifferentElements() {
        LinkedSimpleSet<String> container = new LinkedSimpleSet<>();
        container.addFirst("1");
        container.addFirst("2");
        container.addFirst("3");

        assertThat(container.size(), is(3));
    }

    @Test
    public void whenAddFirstMoreOfTheSameElementsThenAddOnlyOne() {
        LinkedSimpleSet<String> container = new LinkedSimpleSet<>();
        container.addFirst("1");
        container.addFirst("1");
        container.addFirst("1");

        assertThat(container.size(), is(1));
    }

    @Test
    public void whenThenAddLastOneElement() {
        LinkedSimpleSet<String> container = new LinkedSimpleSet<>();
        container.addLast("1");

        assertThat(container.size(), is(1));
    }

    @Test
    public void whenThenAddLastManyDifferentElements() {
        LinkedSimpleSet<String> container = new LinkedSimpleSet<>();
        container.addLast("1");
        container.addLast("2");
        container.addLast("3");

        assertThat(container.size(), is(3));
    }

    @Test
    public void whenAddLastMoreOfTheSameElementsThenAddOnlyOne() {
        LinkedSimpleSet<String> container = new LinkedSimpleSet<>();
        container.addLast("1");
        container.addLast("1");
        container.addLast("1");

        assertThat(container.size(), is(1));
    }
}