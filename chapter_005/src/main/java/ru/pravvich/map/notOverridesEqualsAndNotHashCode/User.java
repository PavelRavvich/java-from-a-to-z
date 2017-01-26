package ru.pravvich.map.notOverridesEqualsAndNotHashCode;

import java.util.Calendar;

public class User {
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

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
