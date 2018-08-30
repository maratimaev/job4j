package ru.job4j.transfer;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.util.*;

public class Transfer {
    Map<User, List<Account>> userAccountsMap = new HashMap<>();


    public void addUser(User user) {
        this.userAccountsMap.putIfAbsent(user, null);
    }

    public void deleteUser(User user) {
        this.userAccountsMap.remove(user);
    }

    private User getUserByPassport(String passport) {
        User result = null;
        for (User current: this.userAccountsMap.keySet()) {
            if (current.getPassport().compareToIgnoreCase(passport) == 0) {
                result = current;
            }
        }
        return result;
    }

    public void addAccountToUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            List<Account> list = this.userAccountsMap.get(user);
            list.add(account);
            this.userAccountsMap.put(user, list);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            List<Account> list = this.userAccountsMap.get(user);
            list.remove(account);
            this.userAccountsMap.put(user, list);
        }
    }

    public List<Account> getUserAccounts (String passport) {
        List<Account> result = null;
        User user = getUserByPassport(passport);
        if (user != null) {
            result  = this.userAccountsMap.get(user);
        }
        return result;
    }

    public boolean transferMoney (String srcPassport, String srcRequisite,
                                  String destPassport, String dstRequisite,
                                  double amount) {
        User srcUser = getUserByPassport(srcPassport);
        User destUser = getUserByPassport(destPassport);

        return false;
    }
}
