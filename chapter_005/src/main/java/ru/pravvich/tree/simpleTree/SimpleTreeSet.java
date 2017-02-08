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

        size++;
        newNode.parent = lastNode;

        if (lastNode.compareTo(newNode) < 0) {
            lastNode.right = newNode;
            return true;
        } else {
            lastNode.left = newNode;
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

        if (compare < 0 && oldLeaf.right != null) {
            lastLeaf = findLastLeaf(oldLeaf.right, newLeaf);
            return lastLeaf;
        }

        if (compare > 0 && oldLeaf.left != null) {
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
        list = new LinkedList<>();
        for (E e : this) {
            list.add(e);
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int count = 0;
            Iterator<Leaf<E>> iterator = new TreeIterator<>(root);

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                count++;
                return iterator.next().element;
            }
        };
    }

    private class TreeIterator<E> implements Iterator<Leaf<E>> {
        private Leaf<E> next;

        private TreeIterator(Leaf<E> root) {
            next = root;
            if (next.left == null) {
                return;
            }

            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Leaf<E> next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Leaf<E> r = next;
            // пока можно вправо, потом до упора влево
            // если нет то вверх пока не дойдем до левой половины
            if (next.right != null) {
                next = next.right;
                while (next.left != null)
                    next = next.left;
                return r;
            } else while (true) {
                if (next.parent == null) {
                    next = null;
                    return r;
                }
                if (next.parent.left == next) {
                    next = next.parent;
                    return r;
                }
                next = next.parent;
            }
        }
    }

    private class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        private Leaf(E element) {
            this.element = element;
        }

        @Override
        public int compareTo(Object obj) {
            Leaf<E> node = (Leaf<E>) obj;
            return this.hashCode() - node.hashCode();
        }

        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + element.hashCode();
            return hash;
        }
    }
}
