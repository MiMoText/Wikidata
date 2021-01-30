package csv;

import org.apache.commons.csv.CSVRecord;
import web.wikidata.WikidataEntityResult;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVFiles {
    private static final Path vocabularies = Paths.get("/", "Volumes", "Ultra Fit", "mimotext", "data", "vocabularies-main", "Themenvokabular.tsv");

    private static final Path results = Paths.get("/", "Volumes", "Ultra Fit", "mimotext", "data", "vocabularies-main", "WikidataVokabular.tsv");

    public static ArrayList<CSVRecord> readVocabulary() throws IOException {
        System.out.println("READ vocabulary");
        return CSV.read(vocabularies);
    }

    public static void writeWikiEntityResults(ArrayList<WikidataEntityResult> list) throws IOException {
        WikidataEntityResult resultForHeader = list.get(0);
        int longest = resultForHeader.getEntitiesSize();
        for (WikidataEntityResult entityResult : list) {
            if (entityResult.getEntitiesSize() > longest) {
                resultForHeader = entityResult;
                longest = resultForHeader.getEntitiesSize();
            }
        }
        CSV.write(results, new ArrayList<>(list), resultForHeader);
        System.out.println("Written results");
    }
}
