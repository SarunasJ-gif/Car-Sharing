package com.sarunas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CarSharingDatabase {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    final static String userName = "root";
    final static String password = "root";


    public static void createCompanyTable() {
        Connection conn = getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS COMPANY" +
                    "(ID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "NAME VARCHAR(255))";
            stmt.executeUpdate(sql);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception es) {
            es.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,userName,password);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println("No connection");
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}










