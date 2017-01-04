package ru.pravvich.tic_tac.location;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void whenPositionCreateThenGettersGetFields() {
        Location position = new Position(1, 2);
        assertThat(position.getX(), is(1));
        assertThat(position.getY(), is(2));
    }

    @Test
    public void whenPositionCreatesThenGettersGetFields() {
        Location position = new Position(3, 2);
        assertThat(position.getX(), is(3));
        assertThat(position.getY(), is(2));
    }
}