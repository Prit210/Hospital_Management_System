package com.example.hostiplemanagementsys.managers;

import java.sql.*;
import java.util.List;

public class Manager {

        private static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management";
        private static final String USER = "root";
        private static final String PASSWORD = "password";

        private static Connection connection;

        // Constructor for establishing database connection
        public Manager() {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to connect to the database.");
            }
        }

        // ---------- Generic CRUD Operations for All Tables ---------- //

        // Create Record
        public static void addRecord(String table, List<String> columns, List<Object> values) {
            StringBuilder query = new StringBuilder("INSERT INTO ").append(table).append(" (");
            for (String column : columns) {
                query.append(column).append(", ");
            }
            query.setLength(query.length() - 2); // Remove last comma
            query.append(") VALUES (");
            for (int i = 0; i < values.size(); i++) {
                query.append("?, ");
            }
            query.setLength(query.length() - 2); // Remove last comma
            query.append(")");

            try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
                for (int i = 0; i < values.size(); i++) {
                    pstmt.setObject(i + 1, values.get(i));
                }
                pstmt.executeUpdate();
                System.out.println("Record added to table: " + table);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Read Records
        public static void getRecords(String table) {
            String query = "SELECT * FROM " + table;
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(metaData.getColumnName(i) + ": " + rs.getObject(i) + " | ");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Update Record
        public static void updateRecord(String table, List<String> columns, List<Object> values, String condition) {
            StringBuilder query = new StringBuilder("UPDATE ").append(table).append(" SET ");
            for (String column : columns) {
                query.append(column).append(" = ?, ");
            }
            query.setLength(query.length() - 2); // Remove last comma
            query.append(" WHERE ").append(condition);

            try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
                for (int i = 0; i < values.size(); i++) {
                    pstmt.setObject(i + 1, values.get(i));
                }
                pstmt.executeUpdate();
                System.out.println("Record updated in table: " + table);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Delete Record
        public static void deleteRecord(String table, String condition) {
            String query = "DELETE FROM " + table + " WHERE " + condition;
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.executeUpdate();
                System.out.println("Record deleted from table: " + table);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // ---------- Specialized Methods for Specific Tables ---------- //

        // Get Appointments by Doctor ID
        public static void getAppointmentsByDoctor(int doctorId) {
            String query = "SELECT * FROM appointments WHERE doctor_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, doctorId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Appointment ID: " + rs.getInt("appointmaint_id") +
                                ", Patient ID: " + rs.getInt("patient_id") +
                                ", Time Slot: " + rs.getString("timeslot") +
                                ", Status: " + rs.getString("status"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Get Patients by Doctor ID
        public static void getPatientsByDoctor(int doctorId) {
            String query = "SELECT * FROM patient WHERE doctor_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, doctorId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("Patient ID: " + rs.getInt("user_id") +
                                ", Name: " + rs.getString("name") +
                                ", Contact: " + rs.getString("contact_number") +
                                ", Medical History: " + rs.getString("medicleHistory"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // ---------- Utility Methods ---------- //

        // Close the database connection
        public static void closeConnection() {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Database connection closed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
