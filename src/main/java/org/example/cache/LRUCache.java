package org.example.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> storage;
    private final LinkedList<Node<K, V>> queue;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    public void put(K key, V value) {
        Node<K, V> currentValue = storage.get(key);
        if (currentValue != null) {
            queue.removeFirstOccurrence(currentValue);
            queue.addLast(currentValue);
        } else {
            if (storage.size() >= capacity) {
                Node<K, V> node = queue.removeFirst();
                storage.remove(node.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            queue.addLast(newNode);
            storage.put(key, newNode);
        }
    }

    public V get(K key) {
        Node<K, V> node = storage.get(key);
        if (node != null) {
            queue.removeFirstOccurrence(node);
            queue.addLast(node);
            return node.value;
        }
        return null;
    }

    private static class Node<K, V> {
        private final K key;
        private final V value;
        private Node<K, V> next;
        private Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
