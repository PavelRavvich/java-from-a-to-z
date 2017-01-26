package ru.pravvich.map.overrideEquals;

import ru.pravvich.map.notOverridesEqualsAndNotHashCode.User;

import java.util.Calendar;

public class UserEquals {

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

    public UserEquals(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;


        UserEquals ue = (UserEquals) obj;

        System.out.println(this.birthday.getTimeInMillis() == ue.getBirthday().getTimeInMillis());
        System.out.println(this.children == ue.getChildren());
        System.out.println(this.name.equals(ue.getName()));
        return this.hashCode() == ue.hashCode() ||
                (this.birthday.getTimeInMillis() == ue.getBirthday().getTimeInMillis() &&
                this.children == ue.getChildren() &&
                this.name.equals(ue.getName()));
    }
}
