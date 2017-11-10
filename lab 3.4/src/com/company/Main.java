package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File input = new File("test.txt");          //TODO: change path to input file
        File output = new File("output.txt");
        if(output.exists()){
            output.delete();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            List<RecordBook> studsList = new ArrayList();
            String line;
            while ((line = br.readLine()) != null) {
                studsList.add(new RecordBook(line));
            }
            System.out.println("Initial students list: ");
            for (RecordBook a : studsList) {
                a.printRecordBook();
                System.out.println();
            }
            System.out.println("Good students: ");
            for (RecordBook a : studsList) {
                if (a.average() >= 5) {
                    a.printRecordBook();
                    a.printRecordBook(output);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }
}
