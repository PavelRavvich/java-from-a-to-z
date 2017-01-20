package ru.pravvich.set.arraySimpleSet;

import java.util.Iterator;
import java.util.Objects;

public class ArraySimpleSet<E> implements SetArray<E> {

    private E[] values;

    public ArraySimpleSet() {
        values = (E[]) new Object[0];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < values.length;
            }

            @Override
            public E next() {
                return values[index++];
            }
        };
    }

    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        if (notExistDuplicate(e)) {
            try {
                E[] temp = values;
                values = (E[]) new Object[temp.length + 1]; // ??? ClassCastException ???
                System.arraycopy(temp, 0, values, 0, temp.length);
                values[values.length - 1] = e;
                sortByHashCode();
                return true;
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int size() {
        return values.length;
    }

    private boolean notExistDuplicate(E elem) {
        for (E e : values) {
            if (e.equals(elem))
                return false;

        }
        return true;
    }

    private void sortByHashCode() {
        int len = (values.length - 1);
        for(int i = len; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (values[j].hashCode() < values[j + 1].hashCode()) {
                    E temp = values[j + 1];
                    values[j + 1] = values[j];
                    values[j] = temp;
                }
            }
        }
    }
}
