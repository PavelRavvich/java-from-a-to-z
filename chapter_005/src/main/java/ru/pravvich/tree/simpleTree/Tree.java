package ru.pravvich.tree.simpleTree;

import java.util.List;

public interface Tree<E> extends Iterable<E> {
    boolean add(E e);
    List<E> get();
    int size();
}
