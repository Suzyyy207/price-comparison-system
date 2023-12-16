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
            List<String[]> platformData = CSVReaderExample.readCSV("C:/Users/24223/Desktop/platform.csv");
            String[] platformColumns = platformData.get(0);//这一行是属性
            platformData.remove(0);
            List<String[]> goodsData = CSVReaderExample.readCSV("C:/Users/24223/Desktop/goods.csv");
            String[] goodsColumns = goodsData.get(0);//这一行是属性
            goodsData.remove(0);
            List<String[]> collectData = CSVReaderExample.readCSV("C:/Users/24223/Desktop/collect.csv");
            String[] collectColumns = collectData.get(0);//这一行是属性
            collectData.remove(0);
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
                    "minPrice DOUBLE,category VARCHAR(255),sellerId INT,platformId INT,productionDate Date,tag INT,state INT,PRIMARY KEY(id)," +
                    "FOREIGN KEY (sellerId) REFERENCES seller(id), " +
                    "FOREIGN KEY (platformId) REFERENCES platform(id) "+
                    ");\n" +
                    "\n";
            String createCollectTableSQL = "CREATE TABLE collect (id INT AUTO_INCREMENT,userId INT,goodsId INT,expectPrice DOUBLE,state INT, PRIMARY KEY(id)," +
                    "FOREIGN KEY (goodsId) REFERENCES goods(id), " +
                    "FOREIGN KEY (userId) REFERENCES user(id)" +
                    ");\n" +
                    "\n";
            String createMessageTableSQL = "CREATE TABLE message (id INT AUTO_INCREMENT,goodsId INT,userId INT,name VARCHAR(255),currentPrice DOUBLE,targetPrice DOUBLE,date DATE,isRead INT, PRIMARY KEY(id)," +
                    "FOREIGN KEY (goodsId) REFERENCES goods(id), " +
                    "FOREIGN KEY (userId) REFERENCES user(id)" +
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

            System.out.println(createCollectTableSQL);
            executeSQL(connection, createCollectTableSQL);
            //collect表的插入
            String insertCollectDataSQL = SQLCreator.generateInsertDataSQL("collect", collectColumns, collectData);
            System.out.println(insertCollectDataSQL);
            String EncodedSQL6 = new String(insertCollectDataSQL.getBytes("UTF-8"), "UTF-8");
            PreparedStatement preparedStatement6 = connection.prepareStatement(EncodedSQL6);
            preparedStatement6.executeUpdate();


            System.out.println(createMessageTableSQL);
            executeSQL(connection, createMessageTableSQL);
            //Message表的插入还没写

            connection.close();
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}