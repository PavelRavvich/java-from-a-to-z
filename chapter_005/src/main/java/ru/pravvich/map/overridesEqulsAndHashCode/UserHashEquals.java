package ru.pravvich.map.overridesEqulsAndHashCode;

import ru.pravvich.map.notOverridesEqualsAndNotHashCode.User;
import ru.pravvich.map.overrideEquals.UserEquals;

import java.util.Calendar;

public class UserHashEquals extends User {
    public UserHashEquals(String name, int children, Calendar birthday) {
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

    @Override
    public int hashCode() {
        int hash = 31;
        hash = hash * 17 + name.hashCode();
        hash = hash *17 + birthday.hashCode();
        hash = hash * 17 + children;
        return hash;
    }
}
