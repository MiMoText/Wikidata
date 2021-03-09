package csv;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class CSVPrintable implements Comparable {
    public CSVPrintable() {}
    public CSVPrintable(CSVRecord csvRecord, CSVRecord csvHeader) {
        this.createFromCSVRecord(csvRecord, csvHeader);
    }

    public abstract void createFromCSVRecord(CSVRecord csvRecord, CSVRecord csvHeader);
    public abstract void printHeaderToCSV(CSVPrinter printer) throws IOException;
    public abstract void printDataToCSV(CSVPrinter printer) throws IOException;

    public void printHeaderRecordToCSV(CSVPrinter printer) throws IOException {
        printHeaderToCSV(printer);
        printer.println();
    }

    public void printDataRecordToCSV(CSVPrinter printer) throws IOException {
        printDataToCSV(printer);
        printer.println();
    }

    public ArrayList<String> parseStringArray(String str) {
        ArrayList<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, "[],");
        while (tokenizer.hasMoreElements()) {
            list.add(tokenizer.nextToken().strip());
        }
        return list;
    }

    public ArrayList<Integer> parseIntegerArray(String str) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> strList = parseStringArray(str);
        for (String s : strList) list.add(Integer.parseInt(s));
        return list;
    }

    public ArrayList<Double> parseDoubleArray(String str) {
        ArrayList<Double> list = new ArrayList<>();
        ArrayList<String> strList = parseStringArray(str);
        for (String s : strList) list.add(Double.parseDouble(s));
        return list;
    }

    public ArrayList<Long> parseLongArray(String str) {
        ArrayList<Long> list = new ArrayList<>();
        ArrayList<String> strList = parseStringArray(str);
        for (String s : strList) list.add(Long.parseLong(s));
        return list;
    }
}
