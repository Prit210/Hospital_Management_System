package com.example.hostiplemanagementsys;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static File logFile;
    private static PrintWriter writer;

    // Enum for log levels
    public enum LogLevel {
        INFO, WARNING, ERROR
    }

    // Constructor to initialize the log file
    public Logger(String filePath) throws IOException {
        logFile = new File("log.txt");
        // Create log file if it does not exist
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        writer = new PrintWriter(new FileWriter(logFile, true));
    }

    // Method to log a message with a specific log level
    public static void log(LogLevel level, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        writer.println("[" + timestamp + "] [" + level + "] " + message);
        writer.flush(); // Ensure the log is written immediately
    }

//    Method to read the log file
    public static String readLog() throws IOException {
        StringBuilder logContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(logFile));
        String line;
        while ((line = reader.readLine()) != null) {
            logContent.append(line).append("\n");
        }
        reader.close();
        return logContent.toString();
    }

    //Method to close the writer (best to do when the app shuts down)
    public static void close() {
        writer.close();
    }
}

// -----------Example of use of logger---------------//
// Create a Logger instance
//Logger logger = new Logger("app_log.txt");
//
// Write some log messages
//            logger.log(LogLevel.INFO, "This is an info message.");
//            logger.log(LogLevel.WARNING, "This is a warning message.");
//            logger.log(LogLevel.ERROR, "This is an error message.");
//
// Read and print the log content
//            System.out.println("Log contents:\n" + logger.readLog());
//
//        Close the logger when done
//        logger.close();