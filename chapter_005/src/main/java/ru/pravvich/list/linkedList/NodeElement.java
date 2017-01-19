package ru.pravvich.list.linkedList;

public class NodeElement<E> {
    private E element;
    private E next;

    public E getNext() {
        return next;
    }

    public void setNext(E next) {
        this.next = next;
    }

    public E getThis() {
        return element;
    }

    public void setThis(E element) {
        this.element = element;
    }
}
