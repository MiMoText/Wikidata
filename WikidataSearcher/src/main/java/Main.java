import csv.CSVFiles;
import org.apache.commons.csv.CSVRecord;
import web.wikidata.WikidataEntity;
import web.wikidata.WikidataEntityRequester;
import web.wikidata.WikidataEntityResult;
import web.wikidata.WikidataSearcher;

import java.util.ArrayList;

public class Main {
    public static void main(String [] args) {
        try {
            ArrayList<WikidataEntityResult> results = new ArrayList<>();

            // Lesen des Vokabulars
            ArrayList<CSVRecord> records = CSVFiles.readVocabulary();

            int lineCount = 0;
            boolean hasHeader = true;
            for (CSVRecord record : records) {
                System.out.printf("\r%d / %d", ++lineCount, records.size());
                if (hasHeader) {
                    hasHeader = false;
                    continue;
                }

                // Bestimmen der Themen (nach Spalte)
                String topic0 = record.get(0);
                String topic1 = record.get(1);
                WikidataEntityResult result = new WikidataEntityResult(topic0, topic1);

                // Suchen nach den Top k (hier: 5) Sucherergebnissen (IDs) auf Wikidata
                WikidataSearcher searcher = new WikidataSearcher();
                ArrayList<String> wikiIDs = searcher.getFirstResultsIDs(topic0, 5);

                // Anfragen des englischen und franzoesischen Labels
                WikidataEntityRequester requester = new WikidataEntityRequester();
                for (String id : wikiIDs) {
                    String label = requester.queryLabel(id);
                    String labelFR = requester.queryFrenchLabel(id);
                    String labelDE = requester.queryGermanLabel(id);
                    result.addEntity(new WikidataEntity(id, label, labelFR, labelDE));
                }
                results.add(result);
            }
            System.out.println();

            // Schreiben der Ergebnisdatei
            CSVFiles.writeWikiEntityResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
