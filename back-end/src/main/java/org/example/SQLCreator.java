package org.example;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SQLCreator{

//    public static String createUserTable(Connection connection) throws SQLException {
//        if(existSQL(connection,"user")){
//            return null;
//        }
//        String filePath = "C:/Users/24223/Desktop/user.txt"; // Replace with the actual path to your file
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // Process each line as needed
//                content.append(line).append("\n");
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading the file: " + e.getMessage());
//        }
//        String fileContent = content.toString();
//        String fileContent="CREATE TABLE user (id INT,name VARCHAR(255),age INT,sex INT,phone VARCHAR(20), PRIMARY KEY(id)\n" +
//                ");\n" +
//                "\n";
//        return fileContent;
//    }

    public static String generateInsertDataSQL(String tableName, String[] columns, List<String[]> data) {
        StringBuilder sql = new StringBuilder("INSERT IGNORE INTO ").append(tableName).append(" (");
        for (String column : columns) {
            sql.append(column).append(", ");
        }
        sql.delete(sql.length() - 2, sql.length()); // Remove the last comma and space

        sql.append(") VALUES ");
        for (String[] rowData : data) {
            sql.append("(");
            for (String value : rowData) {
                if (value == null || value=="") {
                    sql.append("NULL, ");
                    System.out.println(value);
                } else {
                    sql.append("'").append(value).append("', ");
                }
            }
            sql.delete(sql.length() - 2, sql.length()); // Remove the last comma and space
            sql.append("), ");
        }
        sql.delete(sql.length() - 2, sql.length()); // Remove the last comma and space
        sql.append(";");

        return sql.toString();
    }
    private static boolean existSQL(Connection connection, String tableName) throws SQLException {
        ResultSet tables = connection.getMetaData().getTables(null, null, tableName, null);
        return tables.next();
    }

}