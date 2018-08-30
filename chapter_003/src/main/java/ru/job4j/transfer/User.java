package ru.job4j.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String passport;

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result =true;
        User other = (User) obj;
        if ((obj == null)
                || (getClass() != obj.getClass())
                || (!this.name.equals(other.name))
                || (!this.passport.equals(other.passport))) {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }
}
