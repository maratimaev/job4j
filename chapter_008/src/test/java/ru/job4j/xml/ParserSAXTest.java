package ru.job4j.xml;

import org.junit.Test;
import java.io.File;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class ParserSAXTest {

    @Test
    public void whenStartParserGetSum() {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        StoreXML xml = new StoreXML(new File("store.xml"));
        ConvertXSQT xsl = new ConvertXSQT();
        ParserSAX parser = new ParserSAX();

        sql.generate(10);
        xml.save(xml.getColumnFromDB("jdbc:sqlite:xml.s3db", "entry", "field"));
        xsl.convert(new File("store.xml"), new File("converted.xml"), new File("schema.xsl"));
        assertThat(parser.parse(new File("converted.xml")), is(55));
    }

    @Test
    public void startParser() {
        ParserSAX parser = new ParserSAX();
        System.out.println(parser.parse(new File("converted.xml")));
    }
}
