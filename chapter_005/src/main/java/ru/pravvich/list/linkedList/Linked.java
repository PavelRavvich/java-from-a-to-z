package ru.pravvich.list.linkedList;

public interface Linked<E>{
    void addLast(E e);
    void addFirst(E e);
    int size();
    E getElementByIndex(int counter);
}
