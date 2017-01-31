package ru.pravvich.map.userModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class ReferenceBook<K, V> implements Book<K, V> {
    private Node<K, V>[] hashTable;
    private int size = 0;

    ReferenceBook() {
        hashTable = new Node[16];
    }

    public static void main(String[] args) {
        ReferenceBook<String, String> r = new ReferenceBook<>();
        System.out.println(r.insert("key", "value"));

        System.out.println(r.get("key"));

    }
    @Override
    public boolean insert(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        int index = node.hash();
        if (hashTable[index] == null) {
            hashTable[index] = node;
            return true;
        }

        if (hashTable[index].getKey().equals(node.getKey()) &&
                !hashTable[index].getValue().equals(node.getValue()) // одинаковые ключи перезаписывам
                ) {

            hashTable[index].setValue(node.getValue());
            return true;
        }

        if (node.hashCode() == hashTable[index].hashCode() && // обрабатываем коллизию
                !node.getKey().equals(hashTable[index].getKey()) &&
                !node.getValue().equals(hashTable[index].getValue())
                ) {

            hashTable[index].setNext(node);
            return true;
        }

        if (hashTable[index].equals(node)) {
            return false;
        }

        return false;
    }



    @Override
    public boolean delete(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);

        if (hashTable[hash] != null) {
            return hashTable[hash].getValue();
        }
        throw new NullPointerException("Element not found.");
    }

    private int hash(K key) {
        int hash;
        hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    private class Node<K, V> {
        private Node<K, V> next;
        private int hash;
        private K key;
        private V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private int hash() {
            return hashCode() % hashTable.length;
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

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            return hash;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
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
