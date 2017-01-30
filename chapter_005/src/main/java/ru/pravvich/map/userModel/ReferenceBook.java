package ru.pravvich.map.userModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class ReferenceBook<K, V> implements Book<K, V> {
    private Node<K, V>[] hashTable;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private float threshold = hashTable.length * DEFAULT_LOAD_FACTOR;
    private int size = 0;

    ReferenceBook() {
        hashTable = new Node[16];
    }


    @Override
    public boolean insert(K key, V value) {
        return false;
    }



    @Override
    public boolean delete(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int countArray = 0;

            @Override
            public boolean hasNext() {
                return currentCellOfTableHaveMoreElement() || arrayTableHaveMoreCell();
            }

            private boolean arrayTableHaveMoreCell() {
                return countArray < hashTable.length;
            }

            private boolean currentCellOfTableHaveMoreElement() {
                return hashTable[countArray].getNext().getValue() != null;
            }

            @Override
            public V next() {
                return null;
            }

        };
    }


    private class Node<K, V> {
        private Node<K, V> next;
        private int hash;
        private K key;
        private V value;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            initHash();
        }

        private void initHash() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + next.hashCode();
            hash = hash * 17 + value.hashCode();
        }

        public Node<K, V> getNext() {
            return next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj instanceof Node) {
                Node<K, V> node = (Node) obj;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }

            return false;
        }
    }
}
