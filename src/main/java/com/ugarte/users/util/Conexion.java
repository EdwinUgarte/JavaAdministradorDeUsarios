package com.ugarte.users.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static String url = "jdbc:mysql://localhost:3306/usuarios?serverTimezone=GMT-5";
    private static String username = "root";
    private static String password = "1234";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if(connection == null){
            connection =  DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

}
