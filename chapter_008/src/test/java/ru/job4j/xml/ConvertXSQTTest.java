package ru.job4j.xml;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;


/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class ConvertXSQTTest {
    PrintStream stdout = System.out;
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void checkConvert() {
        System.setOut(new PrintStream(this.out));
        ConvertXSQT converter = new ConvertXSQT();
        converter.convert(new File("store.xml"), new File("converted.xml"), new File("schema.xsl"));
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                        .append(System.lineSeparator())
                        .append("<entries>")
                        .append(System.lineSeparator())
                        .append("<entry field=\"0\"/>")
                        .append(System.lineSeparator())
                        .append("</entries>")
                        .toString()
                )
        );
        System.setOut(this.stdout);
    }

}
