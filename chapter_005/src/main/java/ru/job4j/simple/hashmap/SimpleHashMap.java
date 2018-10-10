package ru.job4j.simple.hashmap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.10.2018
 */
public class SimpleHashMap<K, V> implements Iterable {
    /**
     * Коллекция для хранения Node<K, V>
     */
    private Node[] buckets;
    /**
     * Размер коллекции
     */
    private int memSize = 2;
    /**
     * Коэффициент заполнения масива элементами
     */
    private final double full = 0.8;
    /**
     * Количество элементов в массиве
     */
    private int count;

    public SimpleHashMap() {
        this.buckets = new Node[memSize];
    }

    /** Добавление элемента в коллецию
     * @param key ключ типа K
     * @param value значение типа V
     * @return результат тима boolean
     */
    public boolean insert(K key, V value) {
        boolean result = true;
        Node<K, V> bucket = new Node<>(key, value);
        int index = indexFor(bucket.hash, this.memSize);

        if (buckets[index] != null) {
            result = false;
        } else {
            buckets[index] = bucket;
            this.count++;
        }

        if (this.memSize * full <= this.count) {
            extendMap();
        }

        return result;
    }

    /** Получение индекса в массиве на основе хэша
     * @param h хеш типа int
     * @param length размер массиива типа int
     * @return индекс типа int
     */
    private int indexFor(int h, int length) {
        h = h < 0 ? -h : h;
        return h & (length - 1);
    }

    /** Увеличение массива, если достигнут порог
     * размер массива * full равно количеству элементов
     */
    private void extendMap() {
        Node[] nodes = this.buckets;
        memSize *= 2;
        this.buckets = new Node[memSize];

        for (Node<K, V> node : nodes) {
            if (node != null) {
                insert(node.key, node.value);
            }
        }
    }

    /** Получение элемента из коллекции по ключу
     * @param key ключ типа K
     * @return значение типа V
     */
    public V get(K key) {
        V result = null;
        Node<K, V> bucket = new Node<>(key, null);
        int index = indexFor(bucket.hash, this.memSize);
        if (this.buckets[index] != null && bucket.hash == buckets[index].hash) {
            result = (V) this.buckets[index].value;
        }
        return result;
    }

    /** Удаление элемента из коллекции по ключу
     * @param key ключ типа K
     * @return результат типа boolean
     */
    public boolean delete(K key) {
        boolean result = false;
        Node<K, V> bucket = new Node<>(key, null);
        int index = indexFor(bucket.hash, this.memSize);
        if (this.buckets[index] != null && bucket.hash == buckets[index].hash) {
            this.buckets[index] = null;
            result = true;
            this.count--;
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int position = -1;
            @Override
            public boolean hasNext() {
                int i = position;
                boolean result = false;
                while (i < buckets.length - 1) {
                    if (buckets[++i] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @SuppressWarnings("unchecked")
            @Override
            public K next() throws NoSuchElementException {
                while (position < buckets.length - 1) {
                    if (buckets[++position] != null) {
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

    /** Элемент хранения данных
     * @param <K> ключ элемента
     * @param <V> значение элемента
     */
    private static class Node<K, V> {
        int hash;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = hash(this.hashCode());
        }

        private int hash(int h) {
            h = 31 * h;
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

            return this.key != null ? this.key.equals(node.key) : node.key == null;
        }

        @Override
        public int hashCode() {
            return this.key != null ? this.key.hashCode() : 0;
        }
    }
}
