package com.example.hostiplemanagementsys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcDemo {

        public static void main(String[] args) {
            // Database credentials
            String url = "jdbc:mysql://localhost:3306/?user=root"; // Replace 'your_database' with the database name
            String username = "root"; // Replace with your MySQL username
            String password = "#Prit@MYSQL#"; // Replace with your MySQL password

            Connection connection = null;
            Statement statement = null;

            try {
                // Step 1: Register JDBC driver (optional for newer versions)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Step 2: Open a connection
                System.out.println("Connecting to the database...");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection successful!");

                // Step 3: Execute a query
                System.out.println("Creating statement...");
                statement = connection.createStatement();
                String sql = "SELECT id, name, age FROM your_table"; // Replace 'your_table' with the table name
                ResultSet resultSet = statement.executeQuery(sql);

                // Step 4: Process the result set
                while (resultSet.next()) {
                    // Retrieve data by column name
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");

                    // Display values
                    System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
                }

                // Step 5: Clean up
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                // Handle errors
                e.printStackTrace();
            } finally {
                // Ensure resources are closed
                try {
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

