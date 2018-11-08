package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/** Подсчет суммы чисел в xml файле
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class ParserSAX extends DefaultHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());
    /**
     * Сумма чисел
     */
    private Integer fieldsSum = 0;

    public Integer getFieldsCount() {
        return fieldsSum;
    }

    /** Парсинг xml файла с помощью SAX
     * @param source файл converted.xml
     * @return сумма чисел
     */
    public Integer parse(File source) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(source, this);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return this.fieldsSum;
    }

    /** Поиск поля Entry
     * @param namespaceURI типа String
     * @param localName типа String
     * @param qName имя блока
     * @param atts атрибут в блоке
     */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        if (qName.equals("entry")) {
            this.fieldsSum += Integer.parseInt(atts.getValue("field"));
        }
    }
}
