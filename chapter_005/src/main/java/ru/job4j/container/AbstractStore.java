package ru.job4j.container;

import ru.job4j.generic.SimpleArray;

/** Реализация интерфейса Store
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 25.09.2018
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {

    /**
     *  Хранилилище объектов типа Base
     */
    private SimpleArray<E> simpleArray;

    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
    }

    /** Добавление объектов в хранилище
     * @param model объект типа Base и наследники
     */
    @Override
    public void add(E model) {
        this.simpleArray.add(model);
    }

    /** Замена элемента в хранилище
     * @param id элемента типа Base
     * @param model объект типа Base и наследники
     * @return результат типа boolean
     */
    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        E mdl = findById(id);
        if (mdl != null) {
            result = this.simpleArray.set(this.simpleArray.indexOf(mdl), model);
        }
        return result;
    }

    /** Удаление элемента из хранилища
     * @param id элемента типа Base
     * @return типа boolean
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        E model = findById(id);
        if (model != null) {
            result = this.simpleArray.delete(this.simpleArray.indexOf(model));
        }
        return result;
    }

    /** Поиск элеменета в хранилище по индексу
     * @param id индекс элемента
     * @return найденный элемент типа Base и наследники
     */
    @Override
    @SuppressWarnings("unchecked")
    public E findById(String id) {
        E result = null;
        for (E b : (Iterable<E>) this.simpleArray) {
            if (b.getId().compareTo(id) == 0) {
                result = b;
                break;
            }
        }
        return result;
    }
}
