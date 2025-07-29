package com.db.maven.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

////    MySQL database connection properties
//    private static final String URL = "jdbc:mysql://localhost:3306/deutsche";
//    private static final String USER = "root";
//    private static final String PASSWORD = "root";

    //    PostgreSQL database connection properties
    private static final String URL = "jdbc:postgresql://localhost:5432/deutsche";
    private static final String USER = "postgres";
    private static final String PASSWORD = "abhin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
