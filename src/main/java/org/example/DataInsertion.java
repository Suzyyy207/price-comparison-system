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

            List<String[]> roomData = CSVReaderExample.readRoomCSV("C:/Users/24223/Desktop/学习资料/第七学期/数据库设计/lab/lab1/Lab1数据/room.csv");
            String[] roomColumns = roomData.get(0);//这一行是属性
            roomData.remove(0);

            List<String[]> studentData = CSVReaderExample.readStudentCSV("C:/Users/24223/Desktop/学习资料/第七学期/数据库设计/lab/lab1/Lab1数据/student.csv");
            String[] studentColumns = studentData.get(0);//这一行是属性
            studentData.remove(0);

            Connection connection = establishConnection();//建立连接

            //创建room表
            String createRoomTableSQL = SQLCreator.createRoomTable(connection);
            if(createRoomTableSQL==null){System.out.println("创建room表失败！\n");}
            else{
                System.out.println(createRoomTableSQL);
                executeSQL(connection, createRoomTableSQL);

                //room表的插入
                String insertRoomDataSQL = SQLCreator.generateInsertDataSQL("room", roomColumns, roomData);
                System.out.println(insertRoomDataSQL);
                String EncodedSQL1 = new String(insertRoomDataSQL.getBytes("UTF-8"), "UTF-8");
                PreparedStatement preparedStatement1 = connection.prepareStatement(EncodedSQL1);
                preparedStatement1.executeUpdate();
            }




            // 创建student表
            String createStudentTableSQL = SQLCreator.createStudentTable(connection);
            if(createStudentTableSQL==null){System.out.println("创建student表失败！\n");}
            else {
                System.out.println(createStudentTableSQL);
                executeSQL(connection, createStudentTableSQL);

                // student表的插入
                String insertStudentDataSQL = SQLCreator.generateInsertDataSQL("student", studentColumns, studentData);
                System.out.println(insertStudentDataSQL);
                String EncodedSQL2 = new String(insertStudentDataSQL.getBytes("UTF-8"), "UTF-8");
                PreparedStatement preparedStatement2 = connection.prepareStatement(EncodedSQL2);
                preparedStatement2.executeUpdate();
            }

            connection.close();
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
