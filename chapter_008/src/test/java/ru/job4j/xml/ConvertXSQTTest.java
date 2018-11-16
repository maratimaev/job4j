package ru.job4j.xml;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class ConvertXSQTTest {
    @Test
    public void whenConvert1EentryThenCheckStdOut() throws IOException {
        Config config = new Config();
        config.init();
        try (StoreSQL sql = new StoreSQL(config)) {
            sql.generate(0);
        }
        StoreXML xml = new StoreXML(new File("store.xml"));
        ConvertXSQT xsl = new ConvertXSQT();
        xml.save(xml.getColumnFromDB(config, "entry", "field"));
        xsl.convert(new File("store.xml"), new File("converted.xml"), new File("schema.xsl"));
        String expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append(System.lineSeparator())
                .append("<entries>")
                .append(System.lineSeparator())
                .append("<entry field=\"0\"/>")
                .append(System.lineSeparator())
                .append("</entries>")
                .toString();
        String file = null;
        file = new String(Files.readAllBytes(Paths.get("converted.xml")));
        assertThat(file, is(expect));
    }

    @Test
    public void startConverter() {
        ConvertXSQT converter = new ConvertXSQT();
        converter.convert(new File("store.xml"), new File("converted.xml"), new File("schema.xsl"));
    }
}
