package ru.job4j.xml;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.11.2018
 */
public class StoreXMLTest {
    @Test
    public void checkSave() {
        File file = new File("store.xml");
        StoreXML xml = new StoreXML(file);
        xml.setConfigDB("jdbc:sqlite:xml.s3db","entry");
        xml.save(xml.convertToEntries(xml.getColumnFromDB()));
    }
}
