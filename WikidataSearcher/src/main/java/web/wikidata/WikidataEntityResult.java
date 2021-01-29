package web.wikidata;

import csv.CSVPrintable;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;

public class WikidataEntityResult extends CSVPrintable {
    private String topic0;
    private String topic1;
    private ArrayList<WikidataEntity> entities = new ArrayList<>();

    public WikidataEntityResult(String topic0, String topic1) {
        this.topic0 = topic0;
        this.topic1 = topic1;
    }

    public void addEntity(WikidataEntity entity) {
        entities.add(entity);
    }

    @Override
    public void createFromCSVRecord(CSVRecord csvRecord) { }

    @Override
    public void printCSVHeader(CSVPrinter printer) throws IOException {
        printer.print("DEL komplett");
        printer.print("DEL Schnittmenge");
        for (int i = 0; i < entities.size(); i++) {
            //printer.print(entities.get(i).getHeader(i));
            for (String s : entities.get(i).getHeader(i)) printer.print(s);
        }
        printer.println();
    }

    @Override
    public void printCSVRecord(CSVPrinter printer) throws IOException {
        printer.print(topic0);
        printer.print(topic1);
        for (WikidataEntity entity : entities) {
            //printer.print(entity.getInfo());
            for (String s : entity.getInfo()) printer.print(s);
        }
        printer.println();
    }

    @Override
    public int compareTo(Object o) {
        WikidataEntityResult w = (WikidataEntityResult) o;
        return topic0.compareTo(w.topic0);
    }
}
