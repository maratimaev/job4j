package ru.job4j.transfer;

import java.util.*;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Transfer {
    private Map<BankUser, List<Account>> userAccountMap = new HashMap<>();

    /**
     * Метод для поиска счета по реквизиту
     * @param user типа BankUser
     * @param requisite типа String
     * @return Account
     */
    private Account getUserAccountByRequisite(BankUser user, String requisite) {
        Account result = null;
        for (Account current: this.userAccountMap.get(user)) {
            if (current.getRequisites().equals(requisite)) {
                result = current;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для поиска пользователя по паспорту
     * @param passport типа String
     * @return BankUser
     */
    private BankUser getUserByPassport(String passport) {
        BankUser result = null;
        for (BankUser current: this.userAccountMap.keySet()) {
            if (current.getPassport().equals(passport)) {
                result = current;
                break;
            }
        }
        return result;
    }
    /**
     * Геттер HashMap пользователей с привязкой к счетам
     * @param user типа BankUser
     * @param requisite типа String
     * @return Account
     */
    public Map<BankUser, List<Account>> getUserAccountMap() {
        return userAccountMap;
    }

    public void addUser(BankUser user) {
        this.userAccountMap.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(BankUser user) {
        this.userAccountMap.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        BankUser user = getUserByPassport(passport);
        if (user != null) {
            this.userAccountMap.get(user).add(account);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        BankUser user = getUserByPassport(passport);
        if (user != null) {
            this.userAccountMap.get(user).remove(account);
        }
    }

    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;
        BankUser user = getUserByPassport(passport);
        if (user != null) {
            result  = this.userAccountMap.get(user);
        }
        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String destRequisite,
                                  double amount) {
        boolean result = false;

        Account srcAccount = getUserAccountByRequisite(getUserByPassport(srcPassport), srcRequisite);
        Account destAccount = getUserAccountByRequisite(getUserByPassport(destPassport), destRequisite);

        if (srcAccount != null || destAccount != null) {
            if (srcAccount.getValue() > amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                destAccount.setValue(destAccount.getValue() + amount);
                result = true;
            }
        }

        return result;
    }
}
