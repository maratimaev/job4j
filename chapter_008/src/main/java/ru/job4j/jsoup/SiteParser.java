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

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 10.11.2018
 */
public class SiteParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteParser.class.getName());
    private int pagesCount = 2;
    private int messagesCount = 0;
    private ArrayList<SQLRU> sqlruList;

    public SiteParser() {
        this.sqlruList = new ArrayList<>();
    }

    public ArrayList<SQLRU> grabSQLRU(String html, SQLRU last) {
        boolean next = true;
        Date beginYear = formatDate(String.format("01 янв %s, 00:00", new SimpleDateFormat("yyyy").format(new Date())));
        Document doc = htmlConnect(html);
        if (!doc.baseUri().contains("error")) {
            Elements message = doc.select(
                    "td[class=postslisttopic],a[href*=memberinfo],a[href~=https://www.sql.ru/forum/[0-9]+/], td[style=text-align:center][class=altCol],td[style=text-align:center]:not(.altCol)"
            );
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
//                Document body = htmlConnect(sqlru.getMessageLink());
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
                    LOGGER.info(String.format("Loaded %s messages from %s page(s). First messages date: %s",
                            this.messagesCount, this.pagesCount, beginYear));
                    break;
                }
                if (sqlru.getMessage().toLowerCase().contains("java")
                        && !sqlru.getMessage().toLowerCase().contains("javascript")
                        && !sqlru.getMessage().toLowerCase().contains("java script")) {
                    this.sqlruList.add(sqlru);
                    this.messagesCount++;
                }
            }
            String nextPage = String.format("http://www.sql.ru/forum/job-offers/%s", this.pagesCount++);
            if (next) {
                grabSQLRU(nextPage, last);
            }
        }
        return this.sqlruList;
    }

    public Date formatDate(String rawDate) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("d MMM y").format(cal.getTime());
        String today = new SimpleDateFormat("d MMM y").format(new Date());
        try {
            if (rawDate.contains("сегодня")) {
                String time = rawDate.substring(rawDate.indexOf(" "));
                result = new SimpleDateFormat("d MMM y, H:m").parse(today + "," + time);
            } else if (rawDate.contains("вчера")) {
                String time = rawDate.substring(rawDate.indexOf(" "));
                result = new SimpleDateFormat("d MMM y, H:m").parse(yesterday + "," + time);
            } else {
                result = new SimpleDateFormat("d MMM y, H:m").parse(rawDate);
            }
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

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