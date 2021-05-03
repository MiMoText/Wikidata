package csv;

import org.apache.commons.csv.CSVRecord;
import web.wikidata.WikidataEntityResult;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVFiles {
    /**
     * Paths for Vocabulary
     */
    private static final Path vocabularies = Paths.get("Themenvokabular.tsv");
    private static final Path results = Paths.get("WikidataVokabularV3.tsv");

    /**
     * Paths for DEL-File
     */
    private static final Path delFile = Paths.get("DEL_reduziert.tsv");
    private static final Path delFileGerman = Paths.get("DEL_reduziert_de.tsv");

    /*
    METHODS for Vocabulary
     */
    public static ArrayList<CSVRecord> readVocabulary() throws IOException {
        System.out.println("READ vocabulary");
        return CSV.read(vocabularies);
    }

    public static void writeWikiEntityResults(ArrayList<WikidataEntityResult> list) throws IOException {
        CSV.writeCSVPrintables(results, new ArrayList<>(list));
        System.out.println("Written results");
    }

    /*
    METHODS for DEL-File
     */
    public static ArrayList<CSVRecord> readDelFile() throws IOException {
        System.out.println("READ DEL-File: " + delFile.toAbsolutePath().toString());
        return CSV.read(delFile);
    }

    public static void writeDelFileGerman(ArrayList<ArrayList<String>> data) throws IOException {
        CSV.write(delFileGerman, data);
        System.out.println("Written DEL-File with German Labels: " + delFileGerman.toAbsolutePath().toString());
    }
}
