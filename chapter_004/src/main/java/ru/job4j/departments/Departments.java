package ru.job4j.departments;

import java.util.ArrayList;
import java.util.Collections;

public class Departments {
    public String[] sort(String[] strArray) {
        ArrayList <String> rsltArray = new ArrayList<>();
        for (String str: strArray) {
            String[] splited = str.split("\\\\");
            String k = "";
            for (String s: splited) {
                if(!rsltArray.contains(k + s)) {
                    rsltArray.add(k + s);
                }
                k = k + s + "\\";
            }
        }
        Collections.sort(rsltArray);
        return rsltArray.toArray(new String[0]);
    }
}
