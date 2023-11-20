package ru.cs.vsu.eliseev.tests;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Tester {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/database";
        String username = "root";
        String password = "Qwerty098";

        System.out.println("Connecting database ...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
}
