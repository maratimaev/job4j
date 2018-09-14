package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class ConvertListTest {
    /**
     * Тест проверяет корректность преобразования списка массивов в список
     */
    @Test
    public void whenListOfArrayThenList() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = Arrays.asList(new int[]{1, 2}, new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convert(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет корректность преобразования списка массивов в список с помощью Stream API
     */
    @Test
    public void convertList() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = Arrays.asList(new int[]{1, 2}, new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convertStream(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(result, is(expect));
    }
}
