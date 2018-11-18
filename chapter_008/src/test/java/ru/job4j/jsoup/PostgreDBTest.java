package ru.job4j.jsoup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 18.11.2018
 */
public class PostgreDBTest {

    @Test
    public void whenConnectThenGetTrue() {
        try (PostrgreDB db = new PostrgreDB()) {
            assertThat(db.connect(), is(true));
        }
    }

    @Test
    public void whenCheckTableExistanceThenGetExis() {
        try (PostrgreDB db = new PostrgreDB()) {
            assertThat(db.connect(), is(true));
            assertThat(db.tableNotExists(), is(false));
        }
    }

    @Test
    public void whenAddToDB1ItemThenDrop1Item() {
        SQLRU sqlru = new SQLRU();
        sqlru.setMemberName("test");
        sqlru.setMessageLink("test");
        sqlru.setMessage("test");
        sqlru.setDate(new Date());
        ArrayList<SQLRU> sqlrList = new ArrayList<>();
        sqlrList.add(sqlru);
        try (PostrgreDB db = new PostrgreDB()) {
            db.connect();
            db.add(sqlrList);
            assertThat(db.delete(sqlru), is(true));
        }
    }

    @Test
    public void whenGetFromDB1Item() {
        SQLRU sqlru = new SQLRU();
        sqlru.setMemberName("test");
        sqlru.setMessageLink("test");
        sqlru.setMessage("test");
        sqlru.setAnswerCount("0");
        sqlru.setViewsCount("0");
        sqlru.setBody("test");
        sqlru.setDate(new Date());
        ArrayList<SQLRU> sqlruList = new ArrayList<>();
        sqlruList.add(sqlru);
        try (PostrgreDB db = new PostrgreDB()) {
            db.connect();
            db.add(sqlruList);
            sqlruList = db.query("SELECT message, messageLink, memberName, answerCount, viewsCount, body, date FROM messages WHERE message = 'test' AND messageLink = 'test'", new ArrayList<>());
            assertThat(sqlruList.get(0).getMessage(), is("test"));
            db.delete(sqlru);
        }
    }
}
