package ru.pravvich.list.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedContainer<E> implements Linked<E> {

    List list = new LinkedList();

    class Node<E> {
        private E currentElement;
        private E nextElement;
        private E prevElement;

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public void setNextElement(E nextElement) {
            this.nextElement = nextElement;
        }

        public void setPrevElement(E prevElement) {
            this.prevElement = prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public E getNextElement() {
            return nextElement;
        }

        public E getPrevElement() {
            return prevElement;
        }
    }
}


