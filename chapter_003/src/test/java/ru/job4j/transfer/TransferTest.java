package ru.job4j.transfer;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class TransferTest {

    /**
     * Тест проверяет создание пользователя
     */
    @Test
    public void addBankUserTest() {
        BankOperation bankOperation = new BankOperation();
        BankUser user = new BankUser("ivan", "1234");
        bankOperation.addUser(user);
        assertThat(bankOperation.getMapOfUserAccounts().containsKey(user), is(true));
    }

    /**
     * Тест проверяет удаление пользователя
     */
    @Test
    public void deleteBankUserTest() {
        BankOperation bankOperation = new BankOperation();
        BankUser user1 = new BankUser("ivan", "1234");
        bankOperation.addUser(user1);
        bankOperation.deleteUser(user1);
        assertThat(bankOperation.getMapOfUserAccounts().containsKey(user1), is(false));
    }

    /**
     * Тест проверяет добавление счета пользователю
     */
    @Test
    public void addAccountToUserTest() {
        BankOperation bankOperation = new BankOperation();
        BankUser user1 = new BankUser("ivan", "1234");
        bankOperation.addUser(user1);

        bankOperation.addAccountToUser("1234", new Account(1000, "qwerty"));
        bankOperation.addAccountToUser("1234", new Account(2000, "asdfg"));
        assertThat(bankOperation.getMapOfUserAccounts().get(user1).get(1).getRequisites(), is("asdfg"));
    }
    /**
     * Тест проверяет удаление счета у пользователя
     */
    @Test
    public void deleteAccountFromUserTest() {
        BankOperation bankOperation = new BankOperation();
        BankUser user1 = new BankUser("ivan", "1234");
        bankOperation.addUser(user1);

        Account account1 = new Account(1000, "qwerty");
        Account account2 = new Account(2000, "asdfg");
        bankOperation.addAccountToUser("1234", account1);
        bankOperation.addAccountToUser("1234", account2);

        bankOperation.deleteAccountFromUser("1234", account1);
        assertThat(bankOperation.getMapOfUserAccounts().get(user1).contains(account1), is(false));
    }
    /**
     * Тест проверяет вывод счетов пользователя
     */
    @Test
    public void getUserAccountTest() {
        BankOperation bankOperation = new BankOperation();
        BankUser user1 = new BankUser("ivan", "1234");
        bankOperation.addUser(user1);

        Account account1 = new Account(1000, "qwerty");
        Account account2 = new Account(2000, "asdfg");
        bankOperation.addAccountToUser("1234", account1);
        bankOperation.addAccountToUser("1234", account2);

        List<Account> expect = new ArrayList<>();
        expect.add(account1);
        expect.add(account2);
        List<Account> result = bankOperation.getUserAccounts("1234");
        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет перевод денег между счетами
     */
    @Test
    public void transferMoneyTest() {
        BankOperation bankOperation = new BankOperation();

        BankUser userIvan = new BankUser("ivan", "200");
        BankUser userSasha = new BankUser("sasha", "300");
        Account accountIvan1 = new Account(1000, "qwerty");
        Account accountIvan2 = new Account(2000, "asdfg");
        Account accountSasha1 = new Account(3000, "zxcvb");

        List<Account> listAccountIvan = new ArrayList<>();
        List<Account> listAccountSasha = new ArrayList<>();
        listAccountIvan.add(accountIvan1);
        listAccountIvan.add(accountIvan2);
        listAccountSasha.add(accountSasha1);

        bankOperation.getMapOfUserAccounts().put(userIvan, listAccountIvan);
        bankOperation.getMapOfUserAccounts().put(userSasha, listAccountSasha);

        boolean f = bankOperation.transferMoney(userIvan.getPassport(), accountIvan2.getRequisites(),
                userSasha.getPassport(), accountSasha1.getRequisites(),
                100);
        assertThat(f, is(true));
        assertThat(bankOperation.getMapOfUserAccounts().get(userIvan).get(1).getValue(), is((double) 1900));
        assertThat(bankOperation.getMapOfUserAccounts().get(userSasha).get(0).getValue(), is((double) 3100));
    }
    /**
     * Тест проверяет удаление счета у пользователя
     */
    @Test
    public void getterAndSetterTest() {
        BankUser user = new BankUser("ivan", "1234");
        assertThat(user.getPassport(), is("1234"));
        assertThat(user.getName(), is("ivan"));
    }
}
