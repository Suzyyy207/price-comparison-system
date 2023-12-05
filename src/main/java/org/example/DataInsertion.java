package org.example;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.List;


public class DataInsertion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void executeSQL(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public static void main(String[] args) {
        try {

            List<String[]> userData = CSVReaderExample.readUserCSV("C:/Users/24223/Desktop/user.csv");
            String[] userColumns = userData.get(0);//这一行是属性
            userData.remove(0);
            Connection connection = establishConnection();//建立连接

            //创建user表
            String createUserTableSQL = SQLCreator.createUserTable(connection);
            if(createUserTableSQL==null){System.out.println("创建user表失败！\n");}
            else{
                System.out.println(createUserTableSQL);
                executeSQL(connection, createUserTableSQL);

                //user表的插入
                String insertUserDataSQL = SQLCreator.generateInsertDataSQL("user", userColumns, userData);
                System.out.println(insertUserDataSQL);
                String EncodedSQL1 = new String(insertUserDataSQL.getBytes("UTF-8"), "UTF-8");
                PreparedStatement preparedStatement1 = connection.prepareStatement(EncodedSQL1);
                preparedStatement1.executeUpdate();
            }
            connection.close();
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
