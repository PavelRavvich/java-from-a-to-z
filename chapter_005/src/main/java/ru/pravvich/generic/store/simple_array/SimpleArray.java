package ru.pravvich.generic.store.simple_array;

public class SimpleArray<E> extends ArrayIterator<E> implements Simple<E> {
    @Override
    public boolean add(E elem) {
        try {
            E[] temp = values;
            values = (E[]) new Object[values.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length - 1] = elem;
            return true;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < values.length)
            return values[index];

        throw new IndexOutOfBoundsException();
    }

    @Override
    public void update(int index, E element) {
        if (index >= values.length)
            throw new IndexOutOfBoundsException();

        values[index] = element;
    }

    @Override
    public void delete(int index) {
        if (index >= values.length)
            throw new IndexOutOfBoundsException();

        try {
            E[] temp = values;
            values = (E[]) new Object[values.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            int amountElementForCopy = temp.length - (index + 1);
            System.arraycopy(temp, index + 1, values, index, amountElementForCopy);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        return values.length;
    }
}
