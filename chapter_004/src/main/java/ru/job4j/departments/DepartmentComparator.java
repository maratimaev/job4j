package ru.job4j.departments;

import java.util.Comparator;

public class DepartmentComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        int result = -1;
        result = s1.compareTo(s2);
        if(s1.length() < s2.length()) {
            result = -1;
        }
        return result;
    }
}
