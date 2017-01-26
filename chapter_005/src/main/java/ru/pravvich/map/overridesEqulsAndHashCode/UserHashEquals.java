package ru.pravvich.map.overridesEqulsAndHashCode;

import ru.pravvich.map.notOverridesEqualsAndNotHashCode.User;
import ru.pravvich.map.overrideEquals.UserEquals;

import java.util.Calendar;

public class UserHashEquals {
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

    public UserHashEquals(String name, int children, Calendar birthday) {
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

        UserHashEquals ue = (UserHashEquals) obj;

        return this.hashCode() == ue.hashCode() ||
                (this.birthday.getTimeInMillis() == ue.getBirthday().getTimeInMillis() &&
                        this.children == ue.getChildren() &&
                        this.name.equals(ue.getName()));
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
