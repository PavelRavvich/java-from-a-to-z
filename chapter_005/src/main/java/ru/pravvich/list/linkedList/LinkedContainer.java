package ru.pravvich.list.linkedList;

import java.util.Iterator;

public class LinkedContainer<E> implements Linked<E>, Iterable<E>, DescendingIterator<E> {

    private Node<E> fstNode;
    private Node<E> lstNode;
    private volatile int size = 0;

    public LinkedContainer() {
        lstNode = new Node<E>(null, fstNode, null);
        fstNode = new Node<E>(null, null, lstNode);
    }

    @Override
    public synchronized void addLast(E e) {
        Node<E> prev = lstNode;
        prev.setCurrentElement(e);
        lstNode = new Node<>(null, prev, null);
        prev.setNextNode(lstNode);
        size++;
    }

    @Override
    public synchronized void addFirst(E e) {
        Node<E> next = fstNode;
        next.setCurrentElement(e);
        fstNode = new Node<>(null, null, next);
        next.setPrevNode(fstNode);
        size++;
    }

    @Override
    public synchronized E getElementByIndex(int counter) {
        Node<E> target = fstNode.getNextNode();
        for (int i = 0; i < counter; i++) {
            target = getNextElement(target);
        }
        return target.getCurrentElement();
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextNode();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public synchronized Iterator iterator() {
        return new Iterator<E>() {

            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                return getElementByIndex(counter++);
            }
        };
    }

    @Override
    public synchronized Iterator<E> descendingIterator() {
        return new Iterator<E>() {

            int counter = size - 1;

            @Override
            public boolean hasNext() {
                return counter >= 0;
            }

            @Override
            public E next() {
                return getElementByIndex(counter--);
            }
        };
    }

    private final class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

        private Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        private void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        private void setNextNode(Node<E> nextNode) {
            this.nextElement = nextNode;
        }

        private void setPrevNode(Node<E> prevNode) {
            this.prevElement = prevNode;
        }

        private E getCurrentElement() {
            return currentElement;
        }

        private Node<E> getNextNode() {
            return nextElement;
        }

        private Node<E> getPrevNode() {
            return prevElement;
        }
    }
}