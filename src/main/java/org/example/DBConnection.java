package org.example;

import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {

        final String DB_URL = "jdbc:mysql://localhost:3306/shop_db";
        final String DB_USER = "admin";
        final String DB_PASSWORD = "12345678";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement();

                 ResultSet resultSet = statement.executeQuery("SELECT *\n" +
                         "FROM `good_category`\n" +
                         "WHERE `name` LIKE '%чай%' AND\n" +
                         "`parent_id` IS NOT NULL")) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    // Get other columns as needed

                    System.out.println(id + " " + name);
                }

            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error!");
            e.printStackTrace();
        }
    }
}