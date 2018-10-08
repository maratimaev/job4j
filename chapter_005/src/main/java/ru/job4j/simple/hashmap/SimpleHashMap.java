package ru.job4j.simple.hashmap;

import ru.job4j.list.objarray.ObjArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.10.2018
 */
public class SimpleHashMap<K, V> implements Iterable {

    private Node[] buckets;
    private int memSize = 2;

    public SimpleHashMap() {
        this.buckets = new Node[memSize];
    }

    private int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private void extendMap(Node<K, V> bucket) {
        Node[] nodes = this.buckets;
        memSize *= 2;
        this.buckets = new Node[memSize];

        for (Node<K, V> node : nodes) {
            if (node != null) {
                insert(node.key, node.value);
            }
        }

        insert(bucket.key, bucket.value);
    }

    public boolean insert(K key, V value) {
        boolean result = true;
        Node<K, V> bucket = new Node<K, V>(key, value);
        int index = indexFor(bucket.hash, this.memSize);

        if ((buckets[index] != null) && (key == buckets[index].key || (key != null && key.equals(buckets[index].key)))) {
            result = false;
        } else if (buckets[index] != null) {
            extendMap(bucket);
        } else {
            buckets[index] = bucket;
        }
        return result;
    }

    public V get(K key) {
        V result = null;
        Node<K, V> bucket = new Node<>(key, null);
        int index = indexFor(bucket.hash, this.memSize);
        if (this.buckets[index] != null && bucket.hash == buckets[index].hash) {
            result = (V) this.buckets[index].value;
        }
        return result;
    }

    public boolean delete(K key) {
        boolean result = false;
        Node<K, V> bucket = new Node<>(key, null);
        int index = indexFor(bucket.hash, this.memSize);
        if (this.buckets[index] != null && bucket.hash == buckets[index].hash) {
            this.buckets[index] = null;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int position;
            @Override
            public boolean hasNext() {
                int i = position;
                boolean result = false;
                while (i < buckets.length) {
                    if (buckets[i++] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @SuppressWarnings("unchecked")
            @Override
            public K next() throws NoSuchElementException {
                while (position < buckets.length) {
                    if (buckets[position++] != null) {
                        break;
                    }
                }
                if (position == buckets.length) {
                    throw new NoSuchElementException("No next element");
                }
                return (K) buckets[position].key;
            }
        };
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            hash = hash(this.hashCode());
        }

        private int hash(int h) {
            h ^= (h >>> 20) ^ (h >>> 12);
            return h ^ (h >>> 7) ^ (h >>> 4);
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

            if (this.hash != node.hash) {
                return false;
            }
            if (this.key != null ? !this.key.equals(node.key) : node.key != null) {
                return false;
            }
            return this.value != null ? this.value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
    }
}
