package ru.job4j.Profession;

public class Doctor extends Profession {
    Patient patient = new Patient();
    public boolean Cure(Patient patient) {
        return true;
    }
    public void Diagnose (Patient patient) {

    }
}
