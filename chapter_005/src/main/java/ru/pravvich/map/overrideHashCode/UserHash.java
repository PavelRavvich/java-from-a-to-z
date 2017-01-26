package ru.pravvich.map.overrideHashCode;

import ru.pravvich.map.notOverridesEqualsAndNotHashCode.User;

import java.util.Calendar;

public class UserHash extends User {
    public UserHash(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash = hash * 17 + name.hashCode();
        hash = hash * 17 + birthday.hashCode();
        hash = hash * 17 + children;
        return hash;
    }
}
