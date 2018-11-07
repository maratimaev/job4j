package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 06.11.2018
 */
public class StoreXML {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());
    private File xmlFile;
    private String configDB;
    private String table;

    public void setConfigDB(String configDB, String table) {
        this.configDB = configDB;
        this.table = table;
    }
    public StoreXML(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public ArrayList<Integer> getColumnFromDB() {
        ArrayList<Integer> result = new ArrayList<>();
        StoreSQL sql = new StoreSQL(this.configDB);
        if (sql.connect()) {
            if (sql.tableExists(this.table)) {
                result = sql.getColumn("SELECT field FROM entry", new ArrayList<>(), "field");
            }
        }
        return result;
    }

    public List<Entry> convertToEntries(List<Integer> fields) {
        ArrayList<Entry> list = new ArrayList<>();
        for (Integer field: fields) {
            list.add(new Entry(field));
        }
        return list;
    }

    public void save(List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), System.out);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @XmlRootElement
    public static class Entries {
        private List<Entry> entry;
        public Entries() {
        }
        public Entries(List<Entry> entry) {
            this.entry = entry;
        }
        public List<Entry> getEntry() {
            return entry;
        }
        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }

    @XmlRootElement
    public static class Entry {
        private Integer field;
        public Entry() {
        }
        public Entry(Integer field) {
            this.field = field;
        }
        public Integer getField() {
            return field;
        }
        public void setField(Integer field) {
            this.field = field;
        }
    }
}
