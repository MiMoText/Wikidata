package csv;

import org.apache.commons.csv.CSVRecord;
import web.wikidata.WikidataEntityResult;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVFiles {
    private static final Path vocabularies = Paths.get("/", "Volumes", "Ultra Fit", "mimotext", "data", "vocabularies-main", "Themenvokabular.tsv");

    private static final Path results = Paths.get("/", "Volumes", "Ultra Fit", "mimotext", "data", "vocabularies-main", "WikidataVokabularV3.tsv");

    public static ArrayList<CSVRecord> readVocabulary() throws IOException {
        System.out.println("READ vocabulary");
        return CSV.read(vocabularies);
    }

    public static void writeWikiEntityResults(ArrayList<WikidataEntityResult> list) throws IOException {
        CSV.write(results, new ArrayList<>(list));
        System.out.println("Written results");
    }
}
