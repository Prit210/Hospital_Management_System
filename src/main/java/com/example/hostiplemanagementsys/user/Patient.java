package com.example.hostiplemanagementsys.user;

import com.example.hostiplemanagementsys.Appointment;

import java.time.LocalDate;
import java.util.Scanner;

public class Patient extends User {
    private LocalDate admissionDate;
    private LocalDate dischargeDate;
    private String medicleHistory;
    private double bill;

    public void makeAppointment(){
        Scanner sc=new Scanner(System.in);

        System.out.println("Is this your first case.");
        String ans=sc.nextLine();

        if(ans.equals("yes")){

        }
        else{
            Appointment ap=new Appointment();


        }
    }

    public Patient(int userId, String password, String role, String name, int contactNumber, int age, String gender) {
        super(userId, password, role, name, contactNumber, age, gender);
    }
}
