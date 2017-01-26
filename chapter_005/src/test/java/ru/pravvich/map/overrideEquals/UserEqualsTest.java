package ru.pravvich.map.overrideEquals;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserEqualsTest {
    @Test
    public void whenThen() {
        Calendar calendar = new GregorianCalendar(1988, Calendar.JUNE, 19);
        UserEquals user1 = new UserEquals("Pavel", 0, calendar);
        UserEquals user2 = new UserEquals("Pavel", 0, calendar);

        HashSet<UserEquals> set = new HashSet<>();
        set.add(user1);
        set.add(user2);

        int result = set.size();
        assertThat(result, is(2));
    }
}