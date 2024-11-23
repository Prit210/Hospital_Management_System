package com.example.hostiplemanagementsys.user;
import com.example.hostiplemanagementsys.Logger;
import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;

public class User {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "#Prit@MYSQL#";

    // Instance fields
    private int userId;
    private String password;
    private String role;
    private String name;
    private int contactNumber;
    private int age;
    private String gender;
    public static ArrayList<String> log;
    public ArrayList<String> permissions;

    // Static field to track the currently logged-in user
    private static User currentUser = null;

    // Static method to check if a user exists in the database
    public static boolean exists(String ID) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM users WHERE user_id = ?")) {
            stmt.setString(1, ID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Static method to handle login
    public static User login(String ID, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ? AND password = ?")) {
            stmt.setString(1, ID);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getInt("failed_attempts") < 3) {
                    currentUser = new User(
                            rs.getInt("user_id"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                    Logger.addToLog(log,ID+" logged in.");
                    return currentUser;
                } else {
                    throw new IllegalArgumentException("Account locked due to too many failed login attempts.");
                }
            } else {
                throw new IllegalArgumentException("Invalid credentials.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Instance method to request permission
    public void requestPermission() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Scanner sc=new Scanner(System.in);

             PreparedStatement stmt = conn.prepareStatement("INSERT INTO permission_requests (user_id,permission_demanded) VALUES (?,?)")) {

             System.out.println("Enter permission to be granted ");
             String permission_demanded=sc.nextLine();

             stmt.setInt(1, this.userId);
             stmt.setString(2, permission_demanded);

             stmt.executeUpdate();
             Logger.addToLog(log,"Permission"+permission_demanded+" is requested."  );
             System.out.println(name + " has requested additional permissions.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Constructor
    public User(int userId, String password, String role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", contactNumber=" + contactNumber +
                ", permissions=" + permissions +
                ", log=" + log +
                '}';
    }
}
