package com.example.hostiplemanagementsys.managers;
import com.example.hostiplemanagementsys.Logger;
import com.example.hostiplemanagementsys.managers.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientManager extends Manager {

    // Insert patient record
    void insert(ArrayList<String> log) {
        System.out.println("Enter values for the new patient");

        Scanner sc = new Scanner(System.in);
        List<Object> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        System.out.println("Enter patient name:");
        data.add(sc.nextLine());
        columns.add("name");

        System.out.println("Enter patient password:");
        data.add(sc.nextLine());
        columns.add("password");

        System.out.println("Enter patient contact number:");
        data.add(sc.nextLine());
        columns.add("contact_number");

        System.out.println("Enter patient age:");
        data.add(sc.nextInt());
        columns.add("age");
        sc.nextLine(); 

        System.out.println("Enter patient gender:");
        data.add(sc.nextLine());
        columns.add("gender");

        System.out.println("Enter patient medical history:");
        data.add(sc.nextLine());
        columns.add("medicalHistory");

        System.out.println("Enter patient address:");
        data.add(sc.nextLine());
        columns.add("address");

        System.out.println("Enter permission ID:");
        data.add(sc.nextInt());
        columns.add("permission_id");
        sc.nextLine(); 

        System.out.println("Enter doctor ID assigned to this patient:");
        data.add(sc.nextInt());
        columns.add("doctor_id");
        sc.nextLine(); 

        if(Manager.addRecord("patient", columns, data)){
            Logger.log(Logger.LogLevel.INFO, "Patient with name: " + data.get(0) + " added successfully.");
        }
        else{
            Logger.log(Logger.LogLevel.ERROR, "Patient with name: " + data.get(0) + " already exists.");
        }
    }

    // Update patient record
    void update(ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the patient ID to update:");
        int patientId = sc.nextInt();
        sc.nextLine(); 

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
            System.out.println("6. Medical History");
            System.out.println("7. Address");
            System.out.println("8. Permission ID");
            System.out.println("9. Doctor ID");
            System.out.println("10. Done (Finish Updates)");

            int choice = sc.nextInt();
            sc.nextLine(); 

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
                    System.out.println("Enter new gender:");
                    columns.add("gender");
                    data.add(sc.nextLine());
                    break;

                case 6:
                    System.out.println("Enter new medical history:");
                    columns.add("medicalHistory");
                    data.add(sc.nextLine());
                    break;

                case 7:
                    System.out.println("Enter new address:");
                    columns.add("address");
                    data.add(sc.nextLine());
                    break;

                case 8:
                    System.out.println("Enter new permission ID:");
                    columns.add("permission_id");
                    data.add(sc.nextInt());
                    sc.nextLine(); 
                    break;

                case 9:
                    System.out.println("Enter new doctor ID:");
                    columns.add("doctor_id");
                    data.add(sc.nextInt());
                    sc.nextLine(); 
                    break;

                case 10:
                    isUpdating = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        if (!columns.isEmpty()) {
            Manager.updateRecord("patient", columns, data, "user_id = " + patientId);
            Logger.log(Logger.LogLevel.INFO, "Patient with ID: " + patientId + " updated successfully.");
        } else {
            System.out.println("No updates made.");
            Logger.log(Logger.LogLevel.ERROR, "No updates made.");
        }
    }

    // Delete patient record
    void delete(ArrayList<String> log) {
        System.out.println("Enter the patient ID to delete:");
        Scanner sc = new Scanner(System.in);
        int patientId = sc.nextInt();

        if(Manager.deleteRecord("patient", "user_id = " + patientId)) {
            Logger.log(Logger.LogLevel.INFO, "Patient with ID: " + patientId + " deleted successfully.");
        }else{
            Logger.log(Logger.LogLevel.ERROR, "Couldn't delete Patient with ID: " + patientId );
        }
    }
}
