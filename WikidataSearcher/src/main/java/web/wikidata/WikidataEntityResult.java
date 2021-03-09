package web.wikidata;

import csv.CSVPrintable;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;

public class WikidataEntityResult extends CSVPrintable {
    private String topic0;
    private String topic1;
    private ResultsType resultsType;
    private ArrayList<WikidataEntity> entities;

    public WikidataEntityResult(String topic0, String topic1) {
        this.topic0 = topic0;
        this.topic1 = topic1;
        this.resultsType = ResultsType.NONE;
        this.entities = new ArrayList<>();
    }

    public void setEntities(ResultsType resultsType, ArrayList<WikidataEntity> entities) {
        this.resultsType = resultsType;
        this.entities = entities;
    }

    @Override
    public int compareTo(Object o) {
        WikidataEntityResult w = (WikidataEntityResult) o;
        return topic0.compareTo(w.topic0);
    }

    @Override
    public void createFromCSVRecord(CSVRecord csvRecord, CSVRecord csvHeader) {

    }

    @Override
    public void printHeaderToCSV(CSVPrinter printer) throws IOException {
        printer.print("DEL komplett");
        printer.print("DEL Schnittmenge");
        printer.print("Anzahl der Ergebnisse");
        printer.print("Ergebnisse durch Suche nach");
        printer.print("Ergebnisse (IDs)");
        printer.print("Ergebnisse (URLs)");
    }

    @Override
    public void printDataToCSV(CSVPrinter printer) throws IOException {
        printer.print(topic0);
        printer.print(topic1);
        printer.print(entities.size());
        printer.print(resultsType);
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        for (WikidataEntity entity : entities) {
            ids.add(entity.getId());
            urls.add(entity.getUrl());
        }
        printer.print(ids);
        printer.print(urls);
    }
}
