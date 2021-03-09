import csv.CSVFiles;
import org.apache.commons.csv.CSVRecord;
import web.wikidata.*;

import java.util.ArrayList;
import java.util.HashSet;

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
                String topic0 = record.get(0).strip();
                String topic1 = record.get(1).strip();
                WikidataEntityResult result = new WikidataEntityResult(topic0, topic1);

                // Suchen nach den Top k (hier: 5) Sucherergebnissen (IDs) auf Wikidata
                /*WikidataSearcher searcher = new WikidataSearcher();
                ArrayList<String> wikiIDs = searcher.getFirstResultsIDs(topic0, 5);*/

                // Anfragen des englischen und franzoesischen Labels
                WikidataEntityRequester requester = new WikidataEntityRequester();

                HashSet<String> ids;
                ids = requester.queryIDsWithLabelFrench(topic0);
                ids.addAll(requester.queryIDsWithLabelFrench(topic0.toLowerCase()));
                ResultsType resultsType = ResultsType.LABELFRENCH;
                if (ids.isEmpty()) {
                    ids = requester.queryIDsWithAliasFrench(topic0);
                    ids.addAll(requester.queryIDsWithAliasFrench(topic0.toLowerCase()));
                    resultsType = ResultsType.ALIASFRENCH;
                    if (ids.isEmpty()) {
                        ids = requester.queryIDsWithLabelEnglish(topic0);
                        ids.addAll(requester.queryIDsWithLabelEnglish(topic0.toLowerCase()));
                        resultsType = ResultsType.LABELENGLISH;
                        if (ids.isEmpty()) {
                            ids = requester.queryIDsWithAliasEnglish(topic0);
                            ids.addAll(requester.queryIDsWithAliasEnglish(topic0.toLowerCase()));
                            resultsType = ResultsType.ALIASENGLISH;
                            if (ids.isEmpty()) resultsType = ResultsType.NONE;
                        }
                    }
                }

                ArrayList<WikidataEntity> wikidataEntities = new ArrayList<>();
                for (String id : ids) wikidataEntities.add(new WikidataEntity(id));

                result.setEntities(resultsType, wikidataEntities);
                results.add(result);
                Thread.sleep(1000);
            }
            System.out.println();

            // Schreiben der Ergebnisdatei
            CSVFiles.writeWikiEntityResults(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
