package ru.job4j.tracker;

import java.util.List;

public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, List<Integer> range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Пожалуйста введите цифру");
            } catch (MenuOutException moe) {
                System.out.println("Пожалуйста введите данные в диапазоне меню");
            }
        } while (invalid);
        return value;
    }
}
