package ru.pravvich.tic_tac.users;

import org.junit.Test;
import ru.pravvich.tic_tac.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenSubjectCreateThenGetterGetFields() {
        Subject user = new User(Cell.O, "user");
        assertThat(user.getColor(), is(Cell.O));
        assertThat(user.getName(), is("user"));
    }

    @Test
    public void whenSubjectCreateThenGetterGetFieldsStatement() {
        Subject user = new User(Cell.X, "bot");
        assertThat(user.getColor(), is(Cell.X));
        assertThat(user.getName(), is("bot"));
    }
}