package ru.pravvich.map.overridesEqulsAndHashCode;

import org.junit.Test;
import ru.pravvich.map.notOverridesEqualsAndNotHashCode.User;
import ru.pravvich.map.overrideHashCode.UserHash;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserHashEqualsTest {
    @Test
    public void whenThen() {
        Calendar calendar = new GregorianCalendar(1988, Calendar.JUNE, 19);
        UserHashEquals user1 = new UserHashEquals("Pavel", 0, calendar);
        UserHashEquals user2 = new UserHashEquals("Pavel", 0, calendar);

        Set<UserHashEquals> set = new HashSet<>();
        set.add(user1);
        set.add(user2);

        int result = set.size();
        assertThat(result, is(1));
    }
}