package com.example.hostiplemanagementsys.managers;
import com.example.hostiplemanagementsys.Logger;
import com.example.hostiplemanagementsys.managers.Manager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicineManager extends Manager {

    // Insert a new medicine record
    void insert(ArrayList<String> log) {
        System.out.println("Enter details for the new medicine:");

        Scanner sc = new Scanner(System.in);
        List<Object> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        System.out.println("Enter medicine name:");
        data.add(sc.nextLine());
        columns.add("name");

        System.out.println("Enter manufacturer:");
        data.add(sc.nextLine());
        columns.add("manufacturer");

        System.out.println("Enter price:");
        data.add(sc.nextDouble());
        columns.add("price");
        sc.nextLine(); // Consume the leftover newline

        System.out.println("Enter manufacturing date (YYYY-MM-DD):");
        LocalDate manufacturingDate = LocalDate.parse(sc.nextLine());
        data.add(manufacturingDate);
        columns.add("manufacturingDate");

        System.out.println("Enter expiry date (YYYY-MM-DD):");
        LocalDate expiryDate = LocalDate.parse(sc.nextLine());
        data.add(expiryDate);
        columns.add("expiryDate");

        System.out.println("Enter medicine type:");
        data.add(sc.nextLine());
        columns.add("type");

        Manager.addRecord("medicines", columns, data);

        Logger.log(Logger.LogLevel.INFO, "Medicine with name: " + data.get(0) + " added successfully.");
    }

    // Update medicine record using switch-case
    void update(ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the medicine ID to update:");
        int medicineId = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline

        List<Object> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        boolean isUpdating = true;
        while (isUpdating) {
            System.out.println("\nSelect the field to update:");
            System.out.println("1. Name");
            System.out.println("2. Manufacturer");
            System.out.println("3. Price");
            System.out.println("4. Manufacturing Date");
            System.out.println("5. Expiry Date");
            System.out.println("6. Type");
            System.out.println("7. Done (Finish Updates)");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline

            switch (choice) {
                case 1:
                    System.out.println("Enter new name:");
                    columns.add("name");
                    data.add(sc.nextLine());
                    break;

                case 2:
                    System.out.println("Enter new manufacturer:");
                    columns.add("manufacturer");
                    data.add(sc.nextLine());
                    break;

                case 3:
                    System.out.println("Enter new price:");
                    columns.add("price");
                    data.add(sc.nextDouble());
                    sc.nextLine(); // Consume the leftover newline
                    break;

                case 4:
                    System.out.println("Enter new manufacturing date (YYYY-MM-DD):");
                    columns.add("manufacturingDate");
                    data.add(LocalDate.parse(sc.nextLine()));
                    break;

                case 5:
                    System.out.println("Enter new expiry date (YYYY-MM-DD):");
                    columns.add("expiryDate");
                    data.add(LocalDate.parse(sc.nextLine()));
                    break;

                case 6:
                    System.out.println("Enter new type:");
                    columns.add("type");
                    data.add(sc.nextLine());
                    break;

                case 7:
                    isUpdating = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        if (!columns.isEmpty()) {
            Manager.updateRecord("medicines", columns, data, "medicine_id = " + medicineId);
            Logger.addToLog(log, "Medicine with ID: " + medicineId + " updated successfully.");
        } else {
            System.out.println("No updates made.");
        }
    }

    // Delete medicine record
    void delete(ArrayList<String> log) {
        System.out.println("Enter the medicine ID to delete:");
        Scanner sc = new Scanner(System.in);
        int medicineId = sc.nextInt();

        Manager.deleteRecord("medicines", "medicine_id = " + medicineId);
        Logger.addToLog(log, "Medicine with ID: " + medicineId + " deleted successfully.");
    }
}
