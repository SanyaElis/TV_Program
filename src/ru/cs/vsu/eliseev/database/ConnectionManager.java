package ru.cs.vsu.eliseev.database;

import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
    private static ConnectionManager INSTANCE;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Qwerty098";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement statement;

    public static ConnectionManager getInstance() {
        if (INSTANCE == null) {
            try {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            INSTANCE = new ConnectionManager();
            INSTANCE.connect();
        }
        return INSTANCE;
    }

    public Connection reconnect() {
        try {
            connection.close();
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = createStatement(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void connect() {
        try {
            Properties properties = new Properties();
            properties.setProperty("autoReconnect", "true");
            properties.setProperty("connectTimeout", "50000");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
