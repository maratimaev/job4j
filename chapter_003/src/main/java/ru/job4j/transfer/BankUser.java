package ru.job4j.transfer;

import java.util.Objects;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class BankUser {
    private String name;
    private String passport;

    public BankUser(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        BankUser other = (BankUser) obj;
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
