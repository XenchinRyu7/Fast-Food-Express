package com.fastfoodexpress.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDatabase {
    
    private Statement st;
    private ResultSet rs;
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
    
    public String[] querySql(String query) {
        String[] k = null;

        try {
            st = connect.createStatement();
            rs = st.executeQuery(query);

            int rowCount = 0;
            if (rs.last()) {
                rowCount = rs.getRow();
                rs.beforeFirst();
            }

            k = new String[rowCount * 2];

            int index = 0;
            while (rs.next()) {
                k[index++] = rs.getString(1);
                k[index++] = rs.getString(2);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return k;
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
