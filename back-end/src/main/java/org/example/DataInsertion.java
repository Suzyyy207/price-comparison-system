package org.example;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.List;


public class DataInsertion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "layluhan520";

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

            List<String[]> userData = CSVReaderExample.readCSV("./data/user.csv");
            String[] userColumns = userData.get(0);//这一行是属性
            userData.remove(0);
            List<String[]> sellerData = CSVReaderExample.readCSV("./data/seller.csv");
            String[] sellerColumns = sellerData.get(0);//这一行是属性
            sellerData.remove(0);
            List<String[]> adminData = CSVReaderExample.readCSV("./data/admin.csv");
            String[] adminColumns = adminData.get(0);//这一行是属性
            adminData.remove(0);
            List<String[]> platformData = CSVReaderExample.readCSV("./data/platform.csv");
            String[] platformColumns = platformData.get(0);//这一行是属性
            platformData.remove(0);
            List<String[]> goodsData = CSVReaderExample.readCSV("./data/goods.csv");
            String[] goodsColumns = goodsData.get(0);//这一行是属性
            goodsData.remove(0);
            Connection connection = establishConnection();//建立连接

            //创建user表
            String createUserTableSQL ="CREATE TABLE user (id INT AUTO_INCREMENT,name VARCHAR(255),age INT,sex INT,phone VARCHAR(20), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            String createSellerTableSQL ="CREATE TABLE seller (id INT AUTO_INCREMENT,name VARCHAR(255),address VARCHAR(255), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            String createAdminTableSQL ="CREATE TABLE admin (id INT AUTO_INCREMENT,name VARCHAR(255), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            String createPlatformTableSQL ="CREATE TABLE platform (id INT AUTO_INCREMENT,name VARCHAR(255), PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            String createGoodsTableSQL ="CREATE TABLE goods (id INT AUTO_INCREMENT,name VARCHAR(255), location VARCHAR(255),price DOUBLE,"+
                    "minPrice DOUBLE,category VARCHAR(255),sellerId INT,platformId INT,productionDate Date,tag INT,state INT,PRIMARY KEY(id)\n" +
                    ");\n" +
                    "\n";
            System.out.println(createUserTableSQL);
            executeSQL(connection, createUserTableSQL);
            //user表的插入
            String insertUserDataSQL = SQLCreator.generateInsertDataSQL("user", userColumns, userData);
            System.out.println(insertUserDataSQL);
            String EncodedSQL1 = new String(insertUserDataSQL.getBytes("UTF-8"), "UTF-8");
            PreparedStatement preparedStatement1 = connection.prepareStatement(EncodedSQL1);
            preparedStatement1.executeUpdate();

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

            System.out.println(createPlatformTableSQL);
            executeSQL(connection, createPlatformTableSQL);
            //platform表的插入
            String insertPlatformDataSQL = SQLCreator.generateInsertDataSQL("platform", platformColumns, platformData);
            System.out.println(insertPlatformDataSQL);
            String EncodedSQL4 = new String(insertPlatformDataSQL.getBytes("UTF-8"), "UTF-8");
            PreparedStatement preparedStatement4 = connection.prepareStatement(EncodedSQL4);
            preparedStatement4.executeUpdate();

            System.out.println(createGoodsTableSQL);
            executeSQL(connection, createGoodsTableSQL);
            //goods表的插入
            String insertGoodsDataSQL = SQLCreator.generateInsertDataSQL("goods", goodsColumns, goodsData);
            System.out.println(insertGoodsDataSQL);
            String EncodedSQL5 = new String(insertGoodsDataSQL.getBytes("UTF-8"), "UTF-8");
            PreparedStatement preparedStatement5 = connection.prepareStatement(EncodedSQL5);
            preparedStatement5.executeUpdate();

            connection.close();
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
