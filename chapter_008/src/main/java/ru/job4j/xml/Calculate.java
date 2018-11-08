package ru.job4j.xml;

import java.io.File;
import java.time.Duration;
import java.time.Instant;

/** Подсчет суммы сгенерированных чисел
 * @author Marat Imaev (mailto:imaevmarat@outlook.com)
 * @since 08.11.2018
 */
public class Calculate {
    private StoreSQL sql = new StoreSQL("jdbc:sqlite:xml.s3db");
    private StoreXML xml = new StoreXML(new File("store.xml"));
    private ConvertXSQT xsl = new ConvertXSQT();
    private ParserSAX parser = new ParserSAX();

    /**
     * Вывод в консоль этапы генерации, конвертации и подсчета чисел
     */
    public void calc() {
        Instant start = Instant.now();
        System.out.println("Start generate...");
        sql.generate(1000000);
        System.out.println("Start export to xml...");
        xml.save(xml.getColumnFromDB("jdbc:sqlite:xml.s3db", "entry", "field"));
        System.out.println("Start convert...");
        xsl.convert(new File("store.xml"), new File("converted.xml"), new File("schema.xsl"));
        System.out.println("Start parse...");
        System.out.println(String.format("Sum of fields = %s", parser.parse(new File("converted.xml"))));
        Instant end = Instant.now();
        long diff = Duration.between(start, end).abs().getSeconds();
        System.out.println(String.format("Time spent: %d:%02d:%02d", diff / 3600, (diff % 3600) / 60, (diff % 60)));
    }
}
