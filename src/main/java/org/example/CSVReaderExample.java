package org.example;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderExample {

    public static List<String[]> readRoomCSV(String filePath) {
        List<String[]> data = new ArrayList<>();//列表

        try (
                // 以GBK编码读取CSV文件
                CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "GBK"))
        ) {
            String[] line;//一行String
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return data;
    }
    public static List<String[]> readStudentCSV(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (
                // 以UTF-8编码读取CSV文件
                CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))
        ) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return data;
    }
}
