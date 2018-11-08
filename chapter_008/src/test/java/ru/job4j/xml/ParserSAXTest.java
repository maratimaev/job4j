package ru.job4j.xml;

import org.junit.Test;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class ParserSAXTest {

    @Test
    public void checkParser() {
        ParserSAX parser = new ParserSAX();
        System.out.println(parser.parse(new File("converted.xml")));

    }
}
