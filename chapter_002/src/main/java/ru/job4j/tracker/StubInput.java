package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {
    private final String[] answers;
    private int position = 0;

    public StubInput(final String[] answers) {
        this.answers = answers;
    }

    @Override
    public String ask(String question) {
        return this.answers[this.position++];
    }
    @Override
    public int ask(String question, List<Integer> range) {
//        throw new UnsupportedOperationException("Unsupported operation");
        return Integer.parseInt(this.ask(question));
    }
}
