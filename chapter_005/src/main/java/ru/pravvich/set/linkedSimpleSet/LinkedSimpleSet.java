package ru.pravvich.set.linkedSimpleSet;

import ru.pravvich.list.linkedList.LinkedContainer;

public class LinkedSimpleSet<E> extends LinkedContainer<E> {

    @Override
    public void addLast(E e) {
        if (notExistDuplicate(e))
        super.addLast(e);
    }

    @Override
    public void addFirst(E e) {
        if (notExistDuplicate(e))
        super.addFirst(e);
    }

    private boolean notExistDuplicate(E e) {
        for (E element : this) {
            if (e.equals(element)) {
                return false;
            }
        }
        return true;
    }
}
