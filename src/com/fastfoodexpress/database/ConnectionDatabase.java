package com.fastfoodexpress.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDatabase {
    
    public Statement st;
    public ResultSet rs;
    public Connection connect;
    
    public void ConnectionDatabase() {
        String jdbcUrl, username, password, driver;
        
        jdbcUrl = "jdbc:mysql://localhost:3306/db_fastfoodexpress";
        username = "root";
        password = "";
        driver = "com.mysql.jdbc.Driver";
        
        try {
            Class.forName(driver).newInstance();
            connect = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Koneksi Ke database berhasil");
        } catch(Exception e) {
            System.out.println("" + e.getLocalizedMessage());
        }
    }

    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
