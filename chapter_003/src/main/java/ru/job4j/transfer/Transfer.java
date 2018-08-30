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
            if (current.getPassport().equals(passport)) {
                result = current;
            }
        }
        return result;
    }

    private Account getUserAccountByRequisite(User user, String requisite) {
        Account result = null;
        for (List<Account> list: this.userAccountsMap.values()) {
            for (Account account: list) {
                if (account.getRequisites().equals(requisite)) {
                    result = account;
                }
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

    private Account getActualAccount(User user, Account account) {
        List<Account> list = this.userAccountsMap.get(user);
        return list.get(list.indexOf(account));
    }

    public boolean transferMoney (String srcPassport, String srcRequisite,
                                  String destPassport, String dstRequisite,
                                  double amount) {
        User srcUser = getUserByPassport(srcPassport);
        User destUser = getUserByPassport(destPassport);

        Account srcAccount = getActualAccount(srcUser,)

        for (int i = 0; i < this.userAccountsMap.get(srcUser).size(); i++) {
            if(this.userAccountsMap.get(srcUser).get(i).getRequisites().compareToIgnoreCase(srcRequisite) == 0) {
                double value = this.userAccountsMap.get(srcUser).get(i).getValue();
                this.userAccountsMap.get(srcUser).get(i).setValue(value - amount);
            }
        }

        return false;
    }
}
