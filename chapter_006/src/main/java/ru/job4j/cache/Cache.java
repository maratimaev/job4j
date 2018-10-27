package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 27.10.2018
 */
public class Cache {
    /**
     * Поле для хранения синхронизированных объектов
     */
    private Map<Integer, Base> map = new ConcurrentHashMap<>();

    /** Добавить модель
     * @param model типа Base
     */
    public void add(Base model) {
        if (model != null) {
            this.map.put(model.id, model);
            model.version++;
        }
    }

    /** Обновить модель
     * @param model типа Base
     * @throws OptimisticException если во время обновления другой поток изменил модель
     */
    public void update(Base model) throws OptimisticException {
        if (this.map.containsValue(model)) {
            if (model.version == this.map.get(model.id).version) {
                this.map.put(model.id, model);
            } else {
                throw new OptimisticException("Нарушение доступа");
            }
        } else {
            this.add(model);
        }
        model.version++;
    }

    /** Удалить модель
     * @param model типа Base
     */
    public void delete(Base model) {
        if (model != null) {
            this.map.remove(model.id);
            model.version++;
        }
    }

    /**
     * Модель
     */
    class Base {
        /**
         * Поле ключа hashMap
         */
        private int id;
        /**
         * Поле для проверки нарушения доступа к объекту
         */
        private int version;
        /**
         * Имя модели
         */
        private String name;

        public Base(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
