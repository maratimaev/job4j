package ru.job4j.xml;

import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.11.2018
 */
public class StoreXMLTest {

    @Test
    public void whenGenerate1EntryCheckXML() {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        StoreXML xml = new StoreXML(new File("store.xml"));

        sql.generate(0);
        xml.save(xml.getColumnFromDB("jdbc:sqlite:xml.s3db", "entry", "field"));
        String expect = new StringBuilder()
                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                        .append("<entries>\n")
                        .append("    <entry>\n")
                        .append("        <field>0</field>\n")
                        .append("    </entry>\n")
                        .append("</entries>\n")
                        .toString();
        String file = null;
        try {
            file = new String(Files.readAllBytes(Paths.get("store.xml")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(file, is(expect));
    }

    @Test
    public void checkSaveToConsole()  {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.generate(2);
        StoreXML xml = new StoreXML(new File("store.xml"));
        xml.save(xml.getColumnFromDB("jdbc:sqlite:xml.s3db", "entry", "field"));
    }
}
