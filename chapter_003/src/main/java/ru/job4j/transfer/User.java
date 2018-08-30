package ru.job4j.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String passport;
//    private List<Account> accounts = new ArrayList<>();
//
//    public void addAccount(Account account) {
//        this.accounts.add(account);
//    }
//
//    public List<Account> getAccounts(){
//        return this.accounts;
//    }
//
//    public void removeAccount(Account account) {
//        this.accounts.remove(account);
//    }

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
                || (this.name.compareToIgnoreCase(other.name) != 0)
                || (this.passport.compareToIgnoreCase(other.passport) != 0)) {
            result = false;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }
}
