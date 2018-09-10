package ru.job4j.departments;

import org.junit.Test;
import java.util.TreeSet;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class DepartmentsTest {
    /**
     * Тест проверяет сортировку департаментов
     */
    @Test
    public void sortDepartmentsTest() {
        String[] source = {
                "K1\\SK1",
                "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K1\\SK2",
                "K2\\SK1\\SSK2"
        };
        String[] sorted = {
                "K1",
                "K1\\SK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K1\\SK2",
                "K2",
                "K2\\SK1",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        };
        Departments departments = new Departments(new TreeSet<>());
        assertThat(departments.sort(source), is(sorted));
    }
    /**
     * Тест проверяет сортировку департаментов в обратном порядке
     */
    @Test
    public void sortReverseDepartmentsTest() {
        String[] source = {
                "K1\\SK1",
                "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K1\\SK2",
                "K2\\SK1\\SSK2"
        };
        String[] sorted = {
                "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K2\\SK1",
                "K2",
                "K1\\SK2",
                "K1\\SK1\\SSK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1",
                "K1"
        };
        Departments departments = new Departments(new TreeSet<>(new ReverseDepartments()));
        assertThat(departments.sort(source), is(sorted));
    }
    /**
     * Тест проверяет сортировку пустого списка
     */
    @Test
    public void sortEmptyDepartmentsTest() {
        String[] source = {};
        String[] sorted = {};
        Departments departments = new Departments(new TreeSet<>());
        assertThat(departments.sort(source), is(sorted));
    }
}
