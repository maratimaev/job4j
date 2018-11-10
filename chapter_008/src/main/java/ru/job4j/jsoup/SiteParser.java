package ru.job4j.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 10.11.2018
 */
public class SiteParser {

    public void grabSQLRU(String html) {
        String currentYear = new SimpleDateFormat("yyyy").format(new Date());
        Date beginYear = null;
        try {
            //beginYear = new SimpleDateFormat("d m y").parse(String.format("01 01 %s", currentYear));
            beginYear = new SimpleDateFormat("d M y").parse(String.format("30 10 2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Document doc = htmlConnect(html);
        Elements message = doc.select(
                "td[class=postslisttopic],"
                        + "a[href~=http://www.sql.ru/forum/[0-9]+/],"
                        + "a[href*=memberinfo],"
                        + "td[style=text-align:center][class=altCol],"
                        + "td[style=text-align:center]:not(.altCol)"
                        );
        Iterator<Element> iterator = message.iterator();
        while (iterator.hasNext()) {
            System.out.println("Message : " + iterator.next().text());
            String bodyLink = iterator.next().attr("href");
            System.out.println("Body link : " + bodyLink);
            System.out.println("Member : " + iterator.next().text());
            System.out.println("Otvetov : " + iterator.next().text());
            System.out.println("Prosmotrov : " + iterator.next().text());

            Date date = null;
            try {
                date = new SimpleDateFormat("d MMM y, H:m").parse(iterator.next().text());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Date : " + date);

            if (date.equals(beginYear)) {
                break;
            }


            //if (date.equals(new SimpleDateFormat("d MMM y, H:m").parse()))
//            Document body = htmlConnect(bodyLink);
//            Element bodyMsg = body.selectFirst("td[ style=width:15%] ~ td[class=msgBody]");
//            System.out.println("Body : " + bodyMsg.text());
        }
//        Element nextPage = doc.selectFirst("td[style=text-align:left] a");
//        System.out.println("Next page : " + nextPage.attr("href"));
//        if(nextPage.hasText()) {
//            grabSQLRU(nextPage.attr("href"));
//        }
    }

    public Document htmlConnect(String html) {
        Document result = null;
        try {
            result = Jsoup.connect(html).get();
        } catch (IOException e) {
            e.printStackTrace();
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
    private String date;
}