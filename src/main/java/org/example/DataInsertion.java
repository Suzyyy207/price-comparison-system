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

            List<String[]> userData = CSVReaderExample.readCSV("C:/Users/24223/Desktop/user.csv");
            String[] userColumns = userData.get(0);//这一行是属性
            userData.remove(0);
            List<String[]> sellerData = CSVReaderExample.readCSV("C:/Users/24223/Desktop/seller.csv");
            String[] sellerColumns = sellerData.get(0);//这一行是属性
            sellerData.remove(0);
            List<String[]> adminData = CSVReaderExample.readCSV("C:/Users/24223/Desktop/admin.csv");
            String[] adminColumns = adminData.get(0);//这一行是属性
            adminData.remove(0);
            Connection connection = establishConnection();//建立连接

            //创建user表
            String createUserTableSQL ="CREATE TABLE user (id INT,name VARCHAR(255),age INT,sex INT,phone VARCHAR(20), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            String createSellerTableSQL ="CREATE TABLE seller (id INT,name VARCHAR(255),address VARCHAR(255), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            String createAdminTableSQL ="CREATE TABLE admin (id INT,name VARCHAR(255), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
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

            System.out.println(createSellerTableSQL);
            executeSQL(connection, createSellerTableSQL);
            //Seller表的插入
            String insertSellerDataSQL = SQLCreator.generateInsertDataSQL("Seller", sellerColumns, sellerData);
            System.out.println(insertSellerDataSQL);
            String EncodedSQL2= new String(insertSellerDataSQL.getBytes("UTF-8"), "UTF-8");
            PreparedStatement preparedStatement2 = connection.prepareStatement(EncodedSQL2);
            preparedStatement2.executeUpdate();

            System.out.println(createAdminTableSQL);
            executeSQL(connection, createAdminTableSQL);
            //admin表的插入
            String insertAdminDataSQL = SQLCreator.generateInsertDataSQL("admin", adminColumns, adminData);
            System.out.println(insertAdminDataSQL);
            String EncodedSQL3 = new String(insertAdminDataSQL.getBytes("UTF-8"), "UTF-8");
            PreparedStatement preparedStatement3 = connection.prepareStatement(EncodedSQL3);
            preparedStatement3.executeUpdate();
            connection.close();
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
