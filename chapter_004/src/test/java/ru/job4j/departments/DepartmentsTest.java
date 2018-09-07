package ru.job4j.departments;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class DepartmentsTest {
    /**
     * Тест проверяет вычисление линейной функции
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
        Departments departments = new Departments();
        assertThat(departments.sort(source), is(sorted));
    }
}
