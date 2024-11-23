package com.example.hostiplemanagementsys;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {

    public ArrayList<String> log;

    public static void addToLog(ArrayList<String> log, String s) {
        log.add(s);
    }

    public static void clearLog(ArrayList<String> log) {
        log.clear();
    }

    public static void printLog(ArrayList<String> log) {
        while (log.isEmpty()) {
            System.out.println(log);
        }
    }

    public static void writeLog(ArrayList<String> log) {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
                // Write each element of the ArrayList to the file
                for (String item : log) {
                    writer.write(item);
                    writer.newLine(); // Adds a newline after each item
                }
                System.out.println("ArrayList written to file successfully.");
            } catch (IOException e) {
                System.err.println("Couldn't update the log " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Couldn't update the log" + e.getMessage());
        }
    }
}