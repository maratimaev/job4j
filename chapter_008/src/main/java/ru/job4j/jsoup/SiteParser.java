package ru.job4j.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** Парсер сайта sql.ru
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 10.11.2018
 */
public class SiteParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteParser.class.getName());
    /**
     * Количество запрошенных страниц
     */
    private int pagesCount = 0;
    /**
     * Количество загруженных сообщений
     */
    private int messagesCount = 0;
    /**
     * Список загруженных сообщений
     */
    private ArrayList<SQLRU> sqlruList;

    public SiteParser() {
        this.sqlruList = new ArrayList<>();
    }

    /** ЗАгрузка сообщений с сайта
     * @param html ссылка на сайт
     * @param last последнее сообщение в БД
     * @return список сообщений
     */
    public ArrayList<SQLRU> grabSQLRU(String html, SQLRU last) {
        boolean next = true;
        Date beginYear = formatDate(String.format("01 янв %s, 00:00", new SimpleDateFormat("yyyy").format(new Date())));
        Document doc = htmlConnect(html);
        if (!doc.baseUri().contains("error")) {
            String cssQuery = new StringBuilder()
                    .append("td[class=postslisttopic],")
                    .append("a[href*=memberinfo],")
                    .append("a[href~=https://www.sql.ru/forum/[0-9]+/],")
                    .append("td[style=text-align:center][class=altCol],")
                    .append("td[style=text-align:center]:not(.altCol)")
                    .toString();
            Elements message = doc.select(cssQuery);
            Iterator<Element> iterator = message.iterator();
            while (iterator.hasNext()) {
                SQLRU sqlru = new SQLRU();
                sqlru.setMessage(iterator.next().text());
                sqlru.setMessageLink(iterator.next().attr("href"));
                sqlru.setMemberName(iterator.next().text());
                sqlru.setAnswerCount(iterator.next().text());
                sqlru.setViewsCount(iterator.next().text());
                Date formatedDate = formatDate(iterator.next().text());
                sqlru.setDate(formatedDate);
                Document body = htmlConnect(sqlru.getMessageLink());
//                if(body.hasText()) {
//                    Element bodyMsg = body.selectFirst("td[ style=width:15%] ~ td[class=msgBody]");
//                    sqlru.setBody(bodyMsg.text());
//                }
                boolean parseDelta = false;
                if (last.getMessage() != null) {
                    parseDelta = formatedDate.before(last.getDate());
                }
                if (!sqlru.getMessage().contains("Важно: ") && (parseDelta | formatedDate.before(beginYear))) {
                    next = false;
                    LOGGER.info(String.format("Last message in DB at %s - %s", last.getDate(), last.getMessage()));
                    LOGGER.info(String.format("Loaded %s messages from %s page(s). Begining messages date: %s",
                            this.messagesCount, this.pagesCount + 1, beginYear));
                    break;
                }
                if (sqlru.getMessage().toLowerCase().contains("java")
                        && !sqlru.getMessage().toLowerCase().contains("javascript")
                        && !sqlru.getMessage().toLowerCase().contains("java script")) {
                    this.sqlruList.add(sqlru);
                    this.messagesCount++;
                }
            }
            String nextPage = String.format("http://www.sql.ru/forum/job-offers/%s", (this.pagesCount++) + 2);
            if (next) {
                LOGGER.info(String.format("Parsing page %s", this.pagesCount + 1));
                grabSQLRU(nextPage, last);
            }
        }
        return this.sqlruList;
    }

    /** Форматирование даты сообщения
     * @param rawDate строка может содержать "сегодня", "вчера"
     * @return типа Date
     */
    public Date formatDate(String rawDate) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat dateTime = new SimpleDateFormat("d MMM y, H:m");
        SimpleDateFormat dateOnly = new SimpleDateFormat("d MMM y");
        String yesterday = dateOnly.format(cal.getTime());
        String today = dateOnly.format(new Date());
        try {
            if (rawDate.contains("сегодня")) {
                String time = rawDate.substring(rawDate.indexOf(" "));
                result = dateTime.parse(today + "," + time);
            } else if (rawDate.contains("вчера")) {
                String time = rawDate.substring(rawDate.indexOf(" "));
                result = dateTime.parse(yesterday + "," + time);
            } else {
                result = dateTime.parse(rawDate);
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    /** Получение Jsoup соединения с сайтом
     * @param html ссылка на сайт
     * @return типа Document
     */
    public Document htmlConnect(String html) {
        Document result = new Document("");
        try {
            result = Jsoup.connect(html).get();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}

/**
 * Класс для хранения сообщений с сайта
 */
class SQLRU {
    private String message;
    private String messageLink;
    private String memberName;
    private String answerCount;
    private String viewsCount;
    private String body;
    private Date date;

    public void setBody(String body) {
        this.body = body;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }
    public void setViewsCount(String viewsCount) {
        this.viewsCount = viewsCount;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getMessage() {
        return message;
    }
    public String getMessageLink() {
        return messageLink;
    }
    public String getMemberName() {
        return memberName;
    }
    public String getAnswerCount() {
        return answerCount;
    }
    public String getViewsCount() {
        return viewsCount;
    }
    public Date getDate() {
        return date;
    }
    public String getBody() {
        return body;
    }
}