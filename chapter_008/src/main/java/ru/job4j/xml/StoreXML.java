package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/** Генерация xml файла из БД
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 06.11.2018
 */
public class StoreXML {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());
    /**
     * Целевой файл
     */
    private File xmlFile;

    public StoreXML(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    /** Запрос к БД на получение полей одного столбца
     * @param configDB параметры БД
     * @param table имя таблицы
     * @param column имя столбца
     * @return список полей
     */
    public List<Entry> getColumnFromDB(String configDB, String table, String column) {
        ArrayList<Integer> sqlList;
        ArrayList<Entry> result = new ArrayList<>();
        StoreSQL sql = new StoreSQL(configDB);
        if (sql.connect()) {
            if (sql.tableExists(table)) {
                sqlList = sql.getColumn("SELECT field FROM entry", new ArrayList<>(), column);
                for (Integer element: sqlList) {
                    result.add(new Entry(element));
                }
            }
        }
        return result;
    }

    /** Сохранение сгенерированного xml файла
     * @param list список полей
     */
    public void save(List<Entry> list) {
        if (!list.isEmpty()) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(new Entries(list), new FileOutputStream(xmlFile));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}

@XmlRootElement
class Entries {
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
class Entry {
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