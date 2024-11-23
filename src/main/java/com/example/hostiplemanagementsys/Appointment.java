package com.example.hostiplemanagementsys;

import java.time.LocalDate;

public class Appointment {
    private int appointmentId;
    private String doctorId;
    private String patientId;
    private LocalDate appointmentDate;
    private String appointmentTime;

    public void getDetails() {
        try {
            //fetch data from database


        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId+ ", doctorId=" + doctorId + ", patientId=" + patientId + ", appointmentDate="+appointmentDate+", appointmentTime="+appointmentTime+"]";
    }
}

