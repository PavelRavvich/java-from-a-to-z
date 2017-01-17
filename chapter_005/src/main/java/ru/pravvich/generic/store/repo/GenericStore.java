package ru.pravvich.generic.store.repo;

import ru.pravvich.generic.store.Base;
import ru.pravvich.generic.store.simple_array.Simple;
import ru.pravvich.generic.store.simple_array.SimpleArray;

public class GenericStore<T extends Base> implements Store<T> {
    private Simple<T> subjects = new SimpleArray<>();

    @Override
    public void add(T subject) {
        subjects.add(subject);
    }

    public Simple<T> getStore() {
        return subjects;
    }

    @Override
    public boolean delete(T subject) {
        for (int i = 0; i < subjects.getSize(); i++) {
            if (subject.getId().equals(subjects.get(i).getId())) {
                subjects.delete(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(T oldObj, T newObj) {
        for (int i = 0; i < subjects.getSize(); i++) {
            if (oldObj.getId().equals(subjects.get(i).getId())) {
                subjects.update(i, newObj);
                return true;
            }
        }
        return false;
    }
}
