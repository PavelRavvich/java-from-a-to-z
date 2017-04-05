package ru.pravvich.set.arraySimpleSet;

import java.util.Iterator;
import java.util.Objects;

public class ArraySimpleSet<E> implements SetArray<E> {

    private volatile E[] values;

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
    public synchronized boolean add(E e) {
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
        quickSort(values, 0, values.length - 1);
    }

    private void quickSort(E[] array, int left, int right) {
        if (right > left) {
            int i = left, j = right;
            E tmp;

            int v = array[right].hashCode(); //pivot

            do {
                while (array[i].hashCode() < v)
                    i++;
                while (array[j].hashCode() > v)
                    j--;

                if (i <= j) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                    i++;
                    j--;
                }
            } while (i <= j);

            if (left < j) quickSort(array, left, j);
            if (i < right) quickSort(array, i, right);
        }
    }
}
