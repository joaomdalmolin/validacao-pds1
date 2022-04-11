package com.dalmolin.pds1.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                // TODO: Read db configuration from file/system variables
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/clinica", "root", "root");
            }
            return connection;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}