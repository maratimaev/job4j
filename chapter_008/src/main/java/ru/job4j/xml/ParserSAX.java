package ru.job4j.xml;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class ParserSAX extends DefaultHandler {
    private String field;
    private Integer fieldsCount;

    public Integer getFieldsCount() {
        return fieldsCount;
    }

    public Integer parse(File source) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(source, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.fieldsCount;
    }

    @Override
    public void startDocument() {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
            System.out.println("Тег: " + qName);
            if (qName.equals("entry")) {
                System.out.println("field " + atts.getValue("field"));
            }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("Тег разобран: " + qName);
    }


    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }
}
