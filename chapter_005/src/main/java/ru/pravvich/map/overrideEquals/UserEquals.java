package ru.pravvich.map.overrideEquals;

import ru.pravvich.map.notOverridesEqualsAndNotHashCode.User;

import java.util.Calendar;

public class UserEquals extends User {

    public UserEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
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
        return this.hashCode() == ue.hashCode() ||

                this.birthday.getTimeInMillis() == ue.getBirthday().getTimeInMillis() &&
                this.children == ue.getChildren() &&
                this.name.equals(ue.getName());
    }
}
