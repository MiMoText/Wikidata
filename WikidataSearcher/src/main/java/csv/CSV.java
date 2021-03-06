package csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

class CSV {
    private static final CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter('\t');

    static ArrayList<CSVRecord> read(Path path) throws IOException {
        FileReader reader = new FileReader(path.toFile());
        CSVParser parser = CSVParser.parse(reader, csvFormat);

        ArrayList<CSVRecord> records = new ArrayList<>();
        for (CSVRecord csvRecord : parser) {
            records.add(csvRecord);
        }
        return records;
    }

    static void write(Path path, ArrayList<ArrayList<String>> data) throws IOException {
        CSVPrinter printer = new CSVPrinter(new FileWriter(path.toFile()), csvFormat);
        printer.printRecords(data);
        printer.flush();
        printer.close();
    }

    static void writeCSVPrintables(Path path, ArrayList<CSVPrintable> data) throws IOException {
        writeCSVPrintables(path, data, data.get(0));
    }
    static void writeCSVPrintables(Path path, ArrayList<CSVPrintable> data, CSVPrintable dataToUseForHeader) throws IOException {
        if (data.isEmpty()) return;
        CSVPrinter printer = new CSVPrinter(new FileWriter(path.toFile()), csvFormat);
        dataToUseForHeader.printHeaderRecordToCSV(printer);
        for (CSVPrintable date : data) date.printDataRecordToCSV(printer);
        printer.flush();
        printer.close();
    }
}