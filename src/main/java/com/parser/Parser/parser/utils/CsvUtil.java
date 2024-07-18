package com.parser.Parser.parser.utils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

import java.util.List;

public class CsvUtil {

    public static List<CSVRecord> readCsvFile(InputStream inputStream) throws IOException {
        try (
                Reader reader = new InputStreamReader(inputStream);
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            return csvParser.getRecords();
        }
    }
}