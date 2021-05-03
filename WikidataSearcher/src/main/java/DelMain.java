import csv.CSVFiles;
import org.apache.commons.csv.CSVRecord;
import web.wikidata.WikidataRequester;

import java.util.ArrayList;

public class DelMain {
    /**
     * Main zum Erweitern der DEL_reduziert.tsv um deutsche Labels von Wikidata
     * Pfade ggfs. in csv.CSVFiles anpassen
     * @param args
     */
    public static void main(String [] args) {
        try {
            int indexToQuery = 1;
            boolean hasHeader = true;

            WikidataRequester wikidataRequester = new WikidataRequester();

            ArrayList<CSVRecord> records = CSVFiles.readDelFile();
            ArrayList<ArrayList<String>> recordsList = new ArrayList<>();
            for (CSVRecord record : records) {
                ArrayList<String> recordList = new ArrayList<>();
                for (int i = 0; i < record.size(); i++) {
                    recordList.add(record.get(i));
                }
                recordsList.add(recordList);
                if (hasHeader) {
                    recordList.add(4, "Wikidata Label (de)");
                    hasHeader = false;
                } else {
                    String id = recordList.get(indexToQuery);
                    String germanLabel = wikidataRequester.queryGermanLabel(id);
                    System.out.println(germanLabel);
                    recordList.add(4, germanLabel);
                }
            }

            CSVFiles.writeDelFileGerman(recordsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
