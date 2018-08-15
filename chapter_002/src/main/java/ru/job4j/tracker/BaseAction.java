package ru.job4j.tracker;

public abstract class BaseAction implements UserAction {
    private int menuKey;
    private String menuString;

    public BaseAction(int menuKey, String menuString) {
        this.menuKey = menuKey;
        this.menuString = menuString;
    }

    @Override
    public String info() {
        return this.menuKey + this.menuString;
    }
}
