package ru.job4j.transfer;

import org.junit.Test;

import java.lang.reflect.Array;
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
        Transfer transfer = new Transfer();
        BankUser user = new BankUser("ivan", "1234");
        transfer.addUser(user);
        assertThat(transfer.getUserAccountMap().containsKey(user), is(true));
    }

    /**
     * Тест проверяет удаление пользователя
     */
    @Test
    public void deleteBankUserTest() {
        Transfer transfer = new Transfer();
        BankUser user1 = new BankUser("ivan", "1234");
        transfer.addUser(user1);
        transfer.deleteUser(user1);
        assertThat(transfer.getUserAccountMap().containsKey(user1), is(false));
    }

    /**
     * Тест проверяет добавление счета пользователю
     */
    @Test
    public void addAccountToUserTest() {
        Transfer transfer = new Transfer();
        BankUser user1 = new BankUser("ivan", "1234");
        transfer.addUser(user1);

        transfer.addAccountToUser("1234", new Account(1000, "qwerty"));
        transfer.addAccountToUser("1234", new Account(2000, "asdfg"));
        assertThat(transfer.getUserAccountMap().get(user1).get(1).getRequisites(), is("asdfg"));
    }
    /**
     * Тест проверяет удаление счета у пользователя
     */
    @Test
    public void deleteAccountFromUserTest() {
        Transfer transfer = new Transfer();
        BankUser user1 = new BankUser("ivan", "1234");
        transfer.addUser(user1);

        Account account1 = new Account(1000, "qwerty");
        Account account2 = new Account(2000, "asdfg");
        transfer.addAccountToUser("1234", account1);
        transfer.addAccountToUser("1234", account2);

        transfer.deleteAccountFromUser("1234", account1);
        assertThat(transfer.getUserAccountMap().get(user1).contains(account1), is(false));
    }
    /**
     * Тест проверяет вывод счетов пользователя
     */
    @Test
    public void getUserAccountTest() {
        Transfer transfer = new Transfer();
        BankUser user1 = new BankUser("ivan", "1234");
        transfer.addUser(user1);

        Account account1 = new Account(1000, "qwerty");
        Account account2 = new Account(2000, "asdfg");
        transfer.addAccountToUser("1234", account1);
        transfer.addAccountToUser("1234", account2);

        List<Account> expect = new ArrayList<>();
        expect.add(account1);
        expect.add(account2);
        List<Account> result = transfer.getUserAccounts("1234");
        assertThat(result, is(expect));
    }

    /**
     * Тест проверяет перевод денег между счетами
     */
    @Test
    public void transferMoneyTest() {
        Transfer transfer = new Transfer();

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

        transfer.getUserAccountMap().put(userIvan, listAccountIvan);
        transfer.getUserAccountMap().put(userSasha, listAccountSasha);

        boolean flag = transfer.transferMoney(userIvan.getPassport(), accountIvan2.getRequisites(),
                userSasha.getPassport(), accountSasha1.getRequisites(),
                100);
        assertThat(transfer.getUserAccountMap().get(userIvan).get(1).getValue(), is((double) 1900));
        assertThat(transfer.getUserAccountMap().get(userSasha).get(0).getValue(), is((double) 3100));
    }
}
