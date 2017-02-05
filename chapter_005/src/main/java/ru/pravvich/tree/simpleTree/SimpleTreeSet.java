package ru.pravvich.tree.simpleTree;

import java.util.*;

public class SimpleTreeSet<E> implements Tree<E> {
    private Leaf<E> root;
    private List<E> list;
    private int size = 0;

    public SimpleTreeSet() {
        list = new LinkedList<>();
    }

    @Override
    public boolean add(E e) {
        if (size == 0) {
            return initRootLeaf(e);
        }

        Leaf<E> newNode = new Leaf<>(e);
        Leaf<E> lastNode = findLastLeaf(root, newNode);

        if (lastNode == null) {
            return false;
        }

        // храню элементы для итератора и get
        list.add(newNode.element);

        if (lastNode.compareTo(newNode) > 0) {
            lastNode.right = newNode;
            size++;
            return true;
        } else {
            lastNode.left = newNode;
            size++;
            return true;
        }
    }

    // ищу последний лист дерева
    private Leaf<E> findLastLeaf(
            final Leaf<E> oldLeaf,
            final Leaf<E> newLeaf
    ) {

        Leaf<E> lastLeaf = oldLeaf;
        int compare = oldLeaf.compareTo(newLeaf);

        if (compare > 0 && oldLeaf.right != null) {
            lastLeaf = findLastLeaf(oldLeaf.right, newLeaf);
            return lastLeaf;
        }

        if (compare < 0 && oldLeaf.left != null) {
            lastLeaf = findLastLeaf(oldLeaf.left, newLeaf);
            return lastLeaf;
        }

        if (compare == 0)
            return null;

        return lastLeaf;
    }


    private boolean initRootLeaf(final E e) {
        root = new Leaf<>(e);
        list.add(e);
        size++;
        return true;
    }

    @Override
    public List<E> get() {
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    private class Leaf<E> implements Comparable<E> {
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        private Leaf(E element) {
            this.element = element;
        }

        @Override
        public int compareTo(Object obj) {
            if (obj instanceof SimpleTreeSet.Leaf) {
                Leaf<E> node = (Leaf<E>) obj;
                return this.hashCode() - node.hashCode();
            }
            throw new ClassCastException();
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + element.hashCode();
            return hash;
        }
    }
}
