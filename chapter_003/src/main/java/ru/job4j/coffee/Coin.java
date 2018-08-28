package ru.job4j.coffee;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @version $Id$
 * @since 0.1
 */
public class Coin {
    /* Поле число монет данного номинала */
    private int number;
    /* Поле номинал монеты */
    private int nominal;

    public Coin(int nominal) {
        this.nominal = nominal;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public int getNominal() {
        return nominal;
    }

}
