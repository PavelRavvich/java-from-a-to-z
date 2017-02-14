package ru.pravvich.tree.simpleTree;

import java.util.*;

public class SimpleTreeSet<E> implements Tree<E> {
    private Leaf<E> root;
    private List<E> list;
    private int size = 0;

    public SimpleTreeSet() {
        list = new LinkedList<>();
        root = new Leaf<>(null);
    }

    @Override
    public Leaf<E> find(E elem) {
        Leaf<E> eLeaf = new Leaf<>(elem);
        return binarySearch(root, eLeaf);
    }

    private Leaf<E> binarySearch(Leaf<E> leaf, Leaf<E> eLeaf) {
        int compare = leaf.compareTo(eLeaf);

        if (compare < 0 && leaf.right != null) {
            return binarySearch(leaf.right, eLeaf);
        }

        if (compare > 0 && leaf.left != null) {
            return binarySearch(leaf.left, eLeaf);
        }

        if (compare == 0) {
            return leaf;
        }

        return null;
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
        root.element = e;
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
            goToLeftmost();
        }

        private void goToLeftmost() {
            while (next.left != null) {
                next = next.left;
            }
        }

        @Override
        public boolean hasNext() {
            return next != null && next.element != null;
        }

        @Override
        public Leaf<E> next() {
            Leaf<E> r = next;

            if (next.right != null)
                return goRight(r);

            return goUp(r);
        }

        private Leaf<E> goRight(Leaf<E> r) {
            next = next.right;
            while (next.left != null) {
                next = next.left;
            }
            return r;
        }

        private Leaf<E> goUp(Leaf<E> r) {
            while (true) {
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

    class Leaf<E> implements Comparable<E> {
        private Leaf<E> parent;
        private Leaf<E> right;
        private Leaf<E> left;
        private E element;

        private Leaf(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
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

    @Override
    public boolean teeIsBalanced() {
        Tree<Integer> integers = new SimpleTreeSet<>();
        integers.add(10);
        integers.add(8);
        integers.add(6);

        Leaf<E> root = integers.find(10);

        int lengthRightLeaf = lengthRightLeaf(root);
        int lengthLeftLeaf = lengthLeftLeaf(root);

        if (lengthLeftLeaf > lengthRightLeaf) {
            return (lengthLeftLeaf - lengthRightLeaf) < 2;
        } else {
            return (lengthRightLeaf - lengthLeftLeaf) < 2;
        }

    }

    private int lengthRightLeaf(Leaf<E> leaf) {
        int counter = 0;
        Leaf<E> current = leaf;
        while (current.right != null) {
            current = current.right;
            counter++;
        }
        return counter;
    }

    private int lengthLeftLeaf(Leaf<E> leaf) {
        int counter = 0;
        Leaf<E> current = leaf;
        while (current.left != null) {
            current = current.left;
            counter++;
        }
        return counter;
    }
}
