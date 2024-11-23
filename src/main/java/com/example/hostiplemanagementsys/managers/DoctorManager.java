package com.example.hostiplemanagementsys.managers;

import com.example.hostiplemanagementsys.Logger;
import com.example.hostiplemanagementsys.managers.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorManager extends Manager {

    // Insert a new doctor record
    void insert(ArrayList<String> log) {
        System.out.println("Enter details for the new doctor:");

        Scanner sc = new Scanner(System.in);
        List<Object> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        System.out.println("Enter doctor name:");
        data.add(sc.nextLine());
        columns.add("name");

        System.out.println("Enter doctor password:");
        data.add(sc.nextLine());
        columns.add("password");

        System.out.println("Enter doctor contact number:");
        data.add(sc.nextLine());
        columns.add("contact_number");

        System.out.println("Enter doctor age:");
        data.add(sc.nextInt());
        columns.add("age");
        sc.nextLine(); // Consume the leftover newline

        System.out.println("Enter doctor gender (M/F/Other):");
        data.add(sc.nextLine());
        columns.add("gender");

        System.out.println("Enter doctor specialization:");
        data.add(sc.nextLine());
        columns.add("specialization");

        System.out.println("Enter doctor degree:");
        data.add(sc.nextLine());
        columns.add("degree");

        System.out.println("Enter doctor cabin number:");
        data.add(sc.nextInt());
        columns.add("cabinNumber");
        sc.nextLine(); // Consume the leftover newline

        System.out.println("Enter doctor salary:");
        data.add(sc.nextInt());
        columns.add("salary");
        sc.nextLine(); // Consume the leftover newline

        System.out.println("Enter doctor permission ID:");
        data.add(sc.nextInt());
        columns.add("permission_id");
        sc.nextLine(); // Consume the leftover newline

        System.out.println("Enter doctor shift (e.g., Morning, Afternoon, Evening, Night):");
        data.add(sc.nextLine().toUpperCase());
        columns.add("shift");

        Manager.addRecord("doctor", columns, data);

        Logger.addToLog(log, "Doctor with name: " + data.get(0) + " added successfully.");
    }

    // Update doctor record using switch-case
    void update(ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the doctor ID to update:");
        int doctorId = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        List<Object> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        boolean isUpdating = true;
        while (isUpdating) {
            System.out.println("\nSelect the field to update:");
            System.out.println("1. Name");
            System.out.println("2. Password");
            System.out.println("3. Contact Number");
            System.out.println("4. Age");
            System.out.println("5. Gender");
            System.out.println("6. Specialization");
            System.out.println("7. Degree");
            System.out.println("8. Cabin Number");
            System.out.println("9. Salary");
            System.out.println("10. Permission ID");
            System.out.println("11. Shift");
            System.out.println("12. Done (Finish Updates)");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline

            switch (choice) {
                case 1:
                    System.out.println("Enter new name:");
                    columns.add("name");
                    data.add(sc.nextLine());
                    break;

                case 2:
                    System.out.println("Enter new password:");
                    columns.add("password");
                    data.add(sc.nextLine());
                    break;

                case 3:
                    System.out.println("Enter new contact number:");
                    columns.add("contact_number");
                    data.add(sc.nextLine());
                    break;

                case 4:
                    System.out.println("Enter new age:");
                    columns.add("age");
                    data.add(sc.nextInt());
                    sc.nextLine();
                    break;

                case 5:
                    System.out.println("Enter new gender (M/F/Other):");
                    columns.add("gender");
                    data.add(sc.nextLine());
                    break;

                case 6:
                    System.out.println("Enter new specialization:");
                    columns.add("specialization");
                    data.add(sc.nextLine());
                    break;

                case 7:
                    System.out.println("Enter new degree:");
                    columns.add("degree");
                    data.add(sc.nextLine());
                    break;

                case 8:
                    System.out.println("Enter new cabin number:");
                    columns.add("cabinNumber");
                    data.add(sc.nextInt());
                    sc.nextLine();
                    break;

                case 9:
                    System.out.println("Enter new salary:");
                    columns.add("salary");
                    data.add(sc.nextInt());
                    sc.nextLine();
                    break;

                case 10:
                    System.out.println("Enter new permission ID:");
                    columns.add("permission_id");
                    data.add(sc.nextInt());
                    sc.nextLine();
                    break;

                case 11:
                    System.out.println("Enter new shift (e.g., Morning, Afternoon, Evening, Night):");
                    columns.add("shift");
                    data.add(sc.nextLine().toUpperCase());
                    break;

                case 12:
                    isUpdating = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        if (!columns.isEmpty()) {
            Manager.updateRecord("doctor", columns, data, "user_id = " + doctorId);
            Logger.addToLog(log, "Doctor with ID: " + doctorId + " updated successfully.");
        } else {
            System.out.println("No updates made.");
        }
    }

    // Delete doctor record
    void delete(ArrayList<String> log) {
        System.out.println("Enter the doctor ID to delete:");
        Scanner sc = new Scanner(System.in);
        int doctorId = sc.nextInt();

        Manager.deleteRecord("doctor", "user_id = " + doctorId);
        Logger.addToLog(log, "Doctor with ID: " + doctorId + " deleted successfully.");
    }
}
