package com.niji.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSVFile {

    private static ArrayList<String> getRecordFromLine(String line) {
        ArrayList<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static ArrayList<ArrayList<String>> readCsvFile(String file) throws Exception{
        ArrayList<ArrayList<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }
}