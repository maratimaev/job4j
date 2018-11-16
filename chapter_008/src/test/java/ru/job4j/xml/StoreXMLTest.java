package ru.job4j.xml;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
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
    public void whenGenerate1EntryCheckXML() throws IOException {
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.generate(0);
        }
        StoreXML xml = new StoreXML(new File("store.xml"));
        xml.save(xml.getColumnFromDB(config, "entry", "field"));
        String expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<entries>\n")
                .append("    <entry>\n")
                .append("        <field>0</field>\n")
                .append("    </entry>\n")
                .append("</entries>\n")
                .toString();
        String file = null;
        file = new String(Files.readAllBytes(Paths.get("store.xml")));
        assertThat(file, is(expect));
    }

    @Test
    public void checkSaveToConsole()  {
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.generate(2);
        }
        StoreXML xml = new StoreXML(new File("store.xml"));
        xml.save(xml.getColumnFromDB(config, "entry", "field"));
    }
}
