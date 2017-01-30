package ru.pravvich.map.overrideHashCode;

import java.util.Calendar;

public class UserHash {
    public String name;
    public int children;
    public Calendar birthday;

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public UserHash(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
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
