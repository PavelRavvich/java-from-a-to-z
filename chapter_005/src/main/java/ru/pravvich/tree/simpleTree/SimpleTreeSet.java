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

        // храню элементы для итератора и get
        list.add(newNode.element);

        if (lastNode.compareTo(newNode) > 0) {
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
        quickSort(list, 0, list.size() - 1);
        return new LinkedList<>(list); // чтобы снаружи не поломать было
    }

    private void quickSort(List<E> list, int left, int right) {
        if (right > left) {
            int i = left, j = right;
            E tmp;

            int v = list.get(right).hashCode(); //pivot

            do {
                while (list.get(i).hashCode() < v)
                    i++;
                while (list.get(j).hashCode() > v)
                    j--;

                if (i <= j) {
                    tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                    i++;
                    j--;
                }
            } while (i <= j);

            if (left < j) quickSort(list, left, j);
            if (i < right) quickSort(list, i, right);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }

            private Leaf<E> findRightmostFor(final Leaf<E> current) {
                if (current.right != null)
                    return findRightmostFor(current.right);

                return current;
            }

            private Leaf<E> findLeftmostFor(final Leaf<E> current) {
                if (current.left != null)
                    return findLeftmostFor(current.left);

                return current;
            }

            private Leaf<E> getLeftFrom(final Leaf<E> current) {
                if (current.left != null)
                    return current.right;
                else
                    return current;
            }

            private Leaf<E> getRiteFrom(final Leaf<E> current) {
                if (current.right != null)
                    return current.right;
                else
                    return current;
            }
        };
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
