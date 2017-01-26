package ru.pravvich.map.overrideHashCode;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserHashTest {
    @Test
    public void whenThen() {
        Calendar calendar = new GregorianCalendar(1988, Calendar.JUNE, 19);
        UserHash user1 = new UserHash("Pavel", 0, calendar);
        UserHash user2 = new UserHash("Pavel", 0, calendar);

        Set<UserHash> set = new HashSet<>();
        set.add(user1);
        set.add(user2);

        int result = set.size();
        assertThat(result, is(2));
    }
}