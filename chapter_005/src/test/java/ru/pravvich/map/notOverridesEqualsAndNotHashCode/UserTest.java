package ru.pravvich.map.notOverridesEqualsAndNotHashCode;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenThen() {
        Calendar calendar = new GregorianCalendar(1988, Calendar.JUNE, 19);
        User user1 = new User("Pavel", 0, calendar);
        User user2 = new User("Pavel", 0, calendar);

        Set<User> set = new HashSet<>();
        set.add(user1);
        set.add(user2);

        int result = set.size();
        assertThat(result, is(2));
    }
}