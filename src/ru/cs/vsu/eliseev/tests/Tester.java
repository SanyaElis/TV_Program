package ru.cs.vsu.eliseev.tests;


import java.sql.*;


public class Tester {
    private static final String url = "jdbc:mysql://localhost:3306/database";
    private static final String user = "root";
    private static final String password = "Qwerty098";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) {
//        String query = "select count(*) from tvshow";
        String query = "select idtvshow, genre, description from tvshow";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);
//            stmt.executeUpdate("INSERT INTO channel (idchannel, name, numberOfChannel, review) VALUES (11, 'RenTV', 7, 'Wait')");

            while (rs.next()) {
                int id = rs.getInt(1);
                String genre = rs.getString(2);
                String description = rs.getString(3);
                System.out.printf("id: %d, genre: %s, description: %s %n", id, genre, description);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
