package ru.job4j.Profession;

public class Patient {
    public String name;

    void Patient () {
        this.name = "patient";
    }
    void Patient (String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
