package ru.job4j.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

/** Конвертация xml с помощью xslt
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 07.11.2018
 */
public class ConvertXSQT {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class.getName());

    /** Конвертация через xsl
     * @param source исходный файл store.xml
     * @param dest целевой файл converted.xml
     * @param schema схема schema.xsl
     */
    public void convert(File source, File dest, File schema) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(schema);
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(source);
            transformer.transform(xml, new StreamResult(dest));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
