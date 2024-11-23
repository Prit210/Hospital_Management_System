package com.example.hostiplemanagementsys.managers;
import com.example.hostiplemanagementsys.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StaffManager extends Manager {

    void insert(ArrayList<String> log){
        System.out.println("Enter values of staff");

        Scanner sc=new Scanner(System.in);
        List<Object> data=new ArrayList<>();
        List<String> columns = new ArrayList<>();

        System.out.println("Enter staff name");
        data.add(sc.next());
        columns.add("name");

        System.out.println("Enter staff password");
        data.add(sc.nextLine());
        columns.add("password");

        System.out.println("Enter staff phone number");
        data.add(sc.nextInt());
        columns.add("contact_number");
        sc.nextLine();

        System.out.println("Enter staff role from Nurse , ward boy , driver, pharmasistrs :");
       String role=sc.nextLine().toUpperCase();


        System.out.println("Enter staff salary");
        data.add(sc.nextDouble());
        columns.add("salary");

        System.out.println("Enter staff gender");
        data.add(sc.nextLine());
        columns.add("gender");

        System.out.println("Enter staff permission id");
        data.add(sc.nextInt());
        columns.add("permission_id");
        sc.nextLine();

        System.out.println("Enter staff shift from morning,afternoon , evening , night");
        data.add(sc.nextLine().toUpperCase());
        columns.add("shift");

        Manager.addRecord((String.valueOf(Role.valueOf(role))),columns,data);
        sc.close();
        Logger.addToLog(log, "User with name :" + data.get(0) + " with role :" + role + "added successfully.");
   }

    // Update staff record using switch-case
    void update(ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the staff ID to update:");
        int staffId = sc.nextInt();
        sc.nextLine();

        List<Object> data = new ArrayList<>();
        List<String> columns = new ArrayList<>();

        boolean isUpdating = true;
        while (isUpdating) {
            System.out.println("\nSelect the field to update:");
            System.out.println("1. Name");
            System.out.println("2. Password");
            System.out.println("3. Contact Number");
            System.out.println("4. Salary");
            System.out.println("5. Gender");
            System.out.println("6. Permission ID");
            System.out.println("7. Shift");
            System.out.println("8. Done (Finish Updates)");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter new name:");
                    String name = sc.nextLine();
                    columns.add("name");
                    data.add(name);
                    break;

                case 2:
                    System.out.println("Enter new password:");
                    String password = sc.nextLine();
                    columns.add("password");
                    data.add(password);
                    break;

                case 3:
                    System.out.println("Enter new contact number:");
                    String contactNumber = sc.nextLine();
                    columns.add("contact_number");
                    data.add(contactNumber);
                    break;

                case 4:
                    System.out.println("Enter new salary:");
                    double salary = sc.nextDouble();
                    sc.nextLine(); // Consume the leftover newline
                    columns.add("salary");
                    data.add(salary);
                    break;

                case 5:
                    System.out.println("Enter new gender:");
                    String gender = sc.nextLine();
                    columns.add("gender");
                    data.add(gender);
                    break;

                case 6:
                    System.out.println("Enter new permission ID:");
                    int permissionId = sc.nextInt();
                    sc.nextLine();
                    columns.add("permission_id");
                    data.add(permissionId);
                    break;

                case 7:
                    System.out.println("Enter new shift (e.g., Morning, Afternoon, Evening, Night):");
                    String shift = sc.nextLine().toUpperCase();
                    columns.add("shift");
                    data.add(shift);
                    break;

                case 8:
                    isUpdating = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        if (!columns.isEmpty()) {
            Manager.updateRecord("staff", columns, data, "id = " + staffId);
            Logger.addToLog(log, "Staff with ID: " + staffId + " updated successfully.");
        } else {
            System.out.println("No updates made.");
        }
        sc.close();
    }


    // Delete staff record
    void delete(ArrayList<String> log) {
        System.out.println("Enter the staff ID to delete:");
        Scanner sc = new Scanner(System.in);
        int staffId = sc.nextInt();
        sc.nextLine();

        Manager.deleteRecord("staff", "id = " + staffId);
        Logger.addToLog(log, "Staff with ID: " + staffId + " deleted successfully.");
        sc.close();
    }
}


