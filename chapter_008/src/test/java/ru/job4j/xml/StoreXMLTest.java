package ru.job4j.xml;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.11.2018
 */
public class StoreXMLTest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenGenerate1EntryCheckXML() {
        System.setOut(new PrintStream(this.out));
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.generate(1);
        StoreXML xml = new StoreXML(new File("store.xml"));
        xml.save(xml.getColumnFromDB("jdbc:sqlite:xml.s3db", "entry", "field"));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                        .append("<entries>\n")
                        .append("    <entry>\n")
                        .append("        <field>0</field>\n")
                        .append("    </entry>\n")
                        .append("</entries>\n")
                        .toString()
                )
        );
        System.setOut(this.stdout);
    }

    @Test
    public void checkSaveToConsole()  {
        StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
        sql.generate(1);
        StoreXML xml = new StoreXML(new File("store.xml"));
        xml.save(xml.getColumnFromDB("jdbc:sqlite:xml.s3db", "entry", "field"));
    }
}
